package ru.javaschool.mobileoperator.service;

import ru.javaschool.mobileoperator.domain.Lock;

public interface LockService extends GenericService<Lock, Long> {
    void addLock(String name, Boolean deletedByUser);
}
