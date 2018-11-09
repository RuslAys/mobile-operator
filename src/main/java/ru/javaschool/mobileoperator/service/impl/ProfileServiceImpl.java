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
import ru.javaschool.mobileoperator.domain.TerminalDeviceLock;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.repository.api.LockDao;
import ru.javaschool.mobileoperator.repository.api.OptionDao;
import ru.javaschool.mobileoperator.repository.api.PersonalAccountDao;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceLockDao;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.exceptions.TerminalDeviceException;
import ru.javaschool.mobileoperator.utils.LockHelper;
import ru.javaschool.mobileoperator.utils.OptionHelper;
import ru.javaschool.mobileoperator.utils.RoleHelper;

import java.util.ArrayList;
import java.util.HashSet;
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
    private TerminalDeviceLockDao terminalDeviceLockDao;

    @Autowired
    private RoleHelper roleHelper;

    @Autowired
    private OptionHelper optionHelper;

    @Autowired
    private LockHelper lockHelper;

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
        terminalDevice.getTerminalDeviceLocks().forEach(
                terminalDeviceLock -> ids.add(terminalDeviceLock.getLock().getId())
        );
        if(ids.isEmpty()){
            return lockDao.findAll();
        }
        return lockDao.getLockNotIn(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addLock(UserDetails user, Long terminalDeviceId, Long lockId) {
        TerminalDevice terminalDevice = terminalDeviceDao.find(terminalDeviceId);
        if(!terminalDevice.getTerminalDeviceLocks().isEmpty()){
            throw new TerminalDeviceException("Terminal device is locked");
        }
        Lock lock = lockDao.find(lockId);
        TerminalDeviceLock terminalDeviceLock = new TerminalDeviceLock();
        if(roleHelper.isOnlyUser(user)){
            terminalDeviceLock.setCanBeDeletedByUser(true);
        }else{
            terminalDeviceLock.setCanBeDeletedByUser(false);
        }
        terminalDevice.getTerminalDeviceLocks().add(terminalDeviceLock);
        lock.getTerminalDeviceLocks().add(terminalDeviceLock);
        terminalDeviceLock.setTerminalDevice(terminalDevice);
        terminalDeviceLock.setLock(lock);
        lockDao.update(lock);
        terminalDeviceDao.update(terminalDevice);
        terminalDeviceLockDao.add(terminalDeviceLock);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeLock(UserDetails user, Long id, Long lockId) {
        TerminalDevice terminalDevice = terminalDeviceDao.find(id);
        if(!terminalDevice.getTerminalDeviceLocks().isEmpty()){
            throw new TerminalDeviceException("Terminal device is locked");
        }
        Lock lock = lockDao.find(lockId);
        boolean isUser = roleHelper.isOnlyUser(user);
        TerminalDeviceLock tdl = lockHelper.getTerminalDeviceLock(terminalDevice, lock);
        if(isUser && lockHelper.canBeDeleted(terminalDevice, lock)){
            removeLock(terminalDevice, lock);
        }else if(isUser && !lock.getCanBeDeletedByUser()){
            throw new IllegalArgumentException("Lock can`t be deleted by user");
        }else {
            removeLock(terminalDevice, lock);
        }
        terminalDeviceDao.update(terminalDevice);
        lockDao.update(lock);
        terminalDeviceLockDao.remove(tdl);
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
        if(!terminalDevice.getTerminalDeviceLocks().isEmpty()){
            throw new IllegalArgumentException("Terminal device is locked");
        }
        TariffPlan tariffPlan = tariffDao.find(newTariffId);

        //remove old tariff and options
        removeTdFromOptions(terminalDevice);
        removeTdFromTariffPlan(terminalDevice);
        tariffDao.update(terminalDevice.getTariffPlan());
        terminalDevice.getOptions().forEach(option -> optionDao.update(option));

        //add new tariff and options to terminal device
        List<Option> options = new ArrayList<>(tariffPlan.getOptions());
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

    private void removeLock(TerminalDevice terminalDevice, Lock lock){
        terminalDevice.getTerminalDeviceLocks().removeIf(lock1 -> lock.equals(lock1));
        lock.getTerminalDeviceLocks().removeIf(terminalDevice1 -> terminalDevice.equals(terminalDevice1));
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
