package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.TerminalDevice;

import java.util.List;

public interface LockDao extends GenericDao<Lock, Long> {
    /**
     * Method to find locks except specified locks
     * @param ids specified locks ids
     * @return list with locks
     */
    List<Lock> getLockNotIn(List<Long> ids);

    /**
     * Method to find locks by specified locks ids
     * @param lockIds specified locks ids
     * @return list with locks
     */
    List<Lock> findBy(List<Long> lockIds);
}
