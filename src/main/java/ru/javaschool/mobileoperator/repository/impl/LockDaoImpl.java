package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.repository.LockDao;

@Repository
public class LockDaoImpl extends GenericDaoImpl<Lock, Long>
        implements LockDao {
}
