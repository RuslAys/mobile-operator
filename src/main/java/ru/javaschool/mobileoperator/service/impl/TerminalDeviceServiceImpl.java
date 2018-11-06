package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;
import ru.javaschool.mobileoperator.service.api.TerminalDeviceService;

@Service("terminalDeviceService")
public class TerminalDeviceServiceImpl extends GenericServiceImpl<TerminalDevice, Long>
        implements TerminalDeviceService {

    @Autowired
    private TerminalDeviceDao terminalDeviceDao;

    public TerminalDeviceServiceImpl(@Qualifier("terminalDeviceDaoImpl") GenericDao<TerminalDevice, Long> genericDao) {
        super(genericDao);
        terminalDeviceDao = (TerminalDeviceDao) genericDao;
    }
}
