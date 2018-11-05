package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.*;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.utils.RoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private TerminalDeviceDao terminalDeviceDao;

    @Autowired
    private TariffDao tariffDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private LockDao lockDao;

    @Autowired
    private PersonalAccountDao personalAccountDao;

    @Autowired
    private RoleHelper roleHelper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TerminalDevice getFullCustomerInfoByNumber(String number) {
        if(StringUtils.isEmpty(number)){
            throw new IllegalArgumentException("Number cannot be empty");
        }
        return terminalDeviceDao.getFullTerminalDeviceByNumber(Long.parseLong(number));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TariffPlan getTariffPlanOnTerminalDeviceByNumber(String number) {
        return tariffDao.getTariffByNumber(Long.parseLong(number));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Option> getOptionsOnTerminalDeviceByNumber(String number) {
        return optionDao.getOptionsByNumber(Long.parseLong(number));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TerminalDevice getTerminalDeviceWithLocksByNumber(String number) {
        return terminalDeviceDao.getTerminalDeviceWithLocksByNumber(Long.parseLong(number));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Lock> getLocksNotOnTerminalDevice(TerminalDevice terminalDevice) {
        List<Long> ids = new ArrayList<>();
        terminalDevice.getLocks().forEach(
                lock -> ids.add(lock.getId())
        );
        if(ids.isEmpty()){
            return lockDao.findAll();
        }
        return lockDao.getLockNotIn(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addLock(Long terminalDeviceId, List<Long> lockIds) {
        TerminalDevice terminalDevice = terminalDeviceDao.find(terminalDeviceId);
        List<Lock> locks = lockDao.findBy(lockIds);
        terminalDevice.getLocks().addAll(locks);
        locks.forEach(lock -> lockDao.update(lock));
        terminalDeviceDao.update(terminalDevice);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeLock(UserDetails user, Long id, Long lockId) {
        TerminalDevice terminalDevice = terminalDeviceDao.find(id);
        Lock lock = lockDao.find(lockId);
        boolean isUser = roleHelper.isOnlyUser(user);
        if(isUser && lock.getCanBeDeletedByUser()){
            removeLock(terminalDevice, lock);
        }else if(isUser && !lock.getCanBeDeletedByUser()){
            throw new IllegalArgumentException("Lock can`t be deleted by user");
        }else {
            removeLock(terminalDevice, lock);
        }
        terminalDeviceDao.update(terminalDevice);
        lockDao.update(lock);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<TariffPlan> getTariffsExcept(TariffPlan tariffPlan) {
        return tariffDao.getTariffNotIn(tariffPlan);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeTariff(Long terminalDeviceId, Long newTariffId) {
        TerminalDevice terminalDevice = terminalDeviceDao.find(terminalDeviceId);
        if(!terminalDevice.getLocks().isEmpty()){
            throw new IllegalArgumentException("Terminal device is locked");
        }
        TariffPlan tariffPlan = tariffDao.find(newTariffId);

        //remove old tariff and options
        removeTdFromOptions(terminalDevice);
        removeTdFromTariffPlan(terminalDevice);
        tariffDao.update(terminalDevice.getTariffPlan());
        terminalDevice.getOptions().forEach(option -> optionDao.update(option));

        //add new tariff and options to terminal device
        List<Option> options = optionDao.getOptions(tariffPlan);
        terminalDevice.setTariffPlan(tariffPlan);
        terminalDevice.setOptions(options);

        //add terminal device to tariff and options
        tariffPlan.getTerminalDevices().add(terminalDevice);
        tariffPlan.getOptions().forEach(
                option -> option.getTerminalDevices().add(terminalDevice)
        );

        //update personal account
        int currentMoney = terminalDevice.getPersonalAccount().getMoney();
        terminalDevice.getPersonalAccount().setMoney(currentMoney - tariffPlan.getPrice());
        terminalDeviceDao.update(terminalDevice);
        tariffDao.update(tariffPlan);
        personalAccountDao.update(terminalDevice.getPersonalAccount());
    }

    private int getLockIndex(TerminalDevice terminalDevice, Lock lock){
        return Collections.binarySearch(terminalDevice.getLocks(), lock, (Lock l1, Lock l2) ->
            l1.getId().compareTo(l2.getId())
        );
    }

    private int getTdIndex(TerminalDevice terminalDevice, Lock lock){
        return Collections.binarySearch(lock.getTerminalDevices(), terminalDevice,
                (TerminalDevice td1, TerminalDevice td2) -> td1.getId().compareTo(td2.getId())
        );
    }

    private void removeLock(TerminalDevice terminalDevice, Lock lock){
        int lockIndex = getLockIndex(terminalDevice, lock);
        int tdIndex = getTdIndex(terminalDevice, lock);
        if(lockIndex >= 0){
            terminalDevice.getLocks().remove(lockIndex);
            lock.getTerminalDevices().remove(tdIndex);
        }
    }

    private void removeTdFromOptions(TerminalDevice terminalDevice){
        terminalDevice.getOptions().forEach(
                option -> option.getTerminalDevices()
                        .removeIf(terminalDevice1 -> terminalDevice.equals(terminalDevice1))
        );
    }

    private void removeTdFromTariffPlan(TerminalDevice terminalDevice){
        terminalDevice.getTariffPlan().getTerminalDevices().removeIf(
                terminalDevice1 -> terminalDevice.equals(terminalDevice1));
    }
}
