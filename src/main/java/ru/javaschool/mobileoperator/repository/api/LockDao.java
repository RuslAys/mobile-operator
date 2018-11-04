package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.TerminalDevice;

import java.util.List;

public interface LockDao extends GenericDao<Lock, Long> {
    List<Lock> getLockNotIn(List<Long> ids);

    List<Lock> findBy(List<Long> lockIds);
}
