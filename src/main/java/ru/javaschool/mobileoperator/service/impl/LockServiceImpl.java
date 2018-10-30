package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.LockDao;
import ru.javaschool.mobileoperator.service.api.LockService;

@Service("lockService")
public class LockServiceImpl extends GenericServiceImpl<Lock, Long>
        implements LockService {
    @Autowired
    LockDao lockDao;

    @Autowired
    public LockServiceImpl(@Qualifier("lockDaoImpl") GenericDao<Lock, Long> genericDao) {
        super(genericDao);
        this.lockDao = (LockDao) genericDao;
    }

    public LockServiceImpl() {
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addLock(String name, Boolean deletedByUser) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        Lock lock = new Lock();
        lock.setName(name);
        lock.setCanBeDeletedByUser(deletedByUser);
        add(lock);
    }
}
