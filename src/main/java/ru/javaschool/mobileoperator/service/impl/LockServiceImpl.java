package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.domain.TerminalDeviceLock;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.LockDao;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceLockDao;
import ru.javaschool.mobileoperator.service.api.LockService;
import ru.javaschool.mobileoperator.service.exceptions.TerminalDeviceException;
import ru.javaschool.mobileoperator.utils.LockHelper;
import ru.javaschool.mobileoperator.utils.RoleHelper;

@Service("lockService")
public class LockServiceImpl extends GenericServiceImpl<Lock, Long>
        implements LockService {

    private final Logger logger = LogManager.getLogger(LockServiceImpl.class);

    @Autowired
    private LockDao lockDao;

    @Autowired
    private TerminalDeviceDao terminalDeviceDao;

    @Autowired
    private TerminalDeviceLockDao terminalDeviceLockDao;

    @Autowired
    private RoleHelper roleHelper;

    @Autowired
    private LockHelper lockHelper;

    @Autowired
    public LockServiceImpl(@Qualifier("lockDaoImpl") GenericDao<Lock, Long> genericDao) {
        super(genericDao);
        this.lockDao = (LockDao) genericDao;
    }

    public LockServiceImpl() {
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addLock(String name) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        Lock lock = new Lock();
        lock.setName(name);
        lockDao.add(lock);
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
//        lockDao.update(lock);
//        terminalDeviceDao.update(terminalDevice);
        terminalDeviceLockDao.add(terminalDeviceLock);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeLock(UserDetails user, Long id, Long lockId) {
        TerminalDevice terminalDevice = terminalDeviceDao.find(id);
        Lock lock = lockDao.find(lockId);
        boolean isUser = roleHelper.isOnlyUser(user);
        TerminalDeviceLock tdl = lockHelper.getTerminalDeviceLock(terminalDevice, lock);
        if(isUser && lockHelper.canBeDeleted(terminalDevice, lock)){
            removeLockFromTd(terminalDevice, lock);
        }else if(isUser && !tdl.isCanBeDeletedByUser()){
            throw new IllegalArgumentException("Lock can`t be deleted by user");
        }else {
            removeLockFromTd(terminalDevice, lock);
        }
//        terminalDeviceDao.update(terminalDevice);
//        lockDao.update(lock);
        terminalDeviceLockDao.remove(tdl);
    }

    private void removeLockFromTd(TerminalDevice terminalDevice, Lock lock){
        terminalDevice.getTerminalDeviceLocks().removeIf(tdl-> tdl.getLock().equals(lock));
        lock.getTerminalDeviceLocks().removeIf(tdl -> tdl.getTerminalDevice().equals(terminalDevice));
    }
}
