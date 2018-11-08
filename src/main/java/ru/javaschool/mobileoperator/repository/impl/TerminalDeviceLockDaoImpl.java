package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.TerminalDeviceLock;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceLockDao;

@Repository
public class TerminalDeviceLockDaoImpl extends GenericDaoImpl<TerminalDeviceLock, Long>
        implements TerminalDeviceLockDao {
}
