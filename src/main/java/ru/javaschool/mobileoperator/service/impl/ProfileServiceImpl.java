package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.LockDao;
import ru.javaschool.mobileoperator.repository.api.OptionDao;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;
import ru.javaschool.mobileoperator.service.api.ProfileService;

import java.util.ArrayList;
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
}
