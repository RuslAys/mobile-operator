package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;
import ru.javaschool.mobileoperator.service.api.TerminalDeviceService;

@Service("terminalDeviceService")
public class TerminalDeviceServiceImpl extends GenericServiceImpl<TerminalDevice, Long>
        implements TerminalDeviceService {

    private final Logger logger = LogManager.getLogger(TerminalDeviceServiceImpl.class);

    @Autowired
    private TerminalDeviceDao terminalDeviceDao;

    public TerminalDeviceServiceImpl(@Qualifier("terminalDeviceDaoImpl") GenericDao<TerminalDevice, Long> genericDao) {
        super(genericDao);
        terminalDeviceDao = (TerminalDeviceDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TerminalDevice getFullTerminalDevice(Long id) {
        return terminalDeviceDao.getFullTerminalDeviceById(id);
    }
}
