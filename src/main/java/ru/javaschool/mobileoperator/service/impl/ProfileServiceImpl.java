package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.TerminalDeviceDao;
import ru.javaschool.mobileoperator.service.api.ProfileService;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private TerminalDeviceDao terminalDeviceDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TerminalDevice getTerminalDeviceByNumber(String number) {
        if(StringUtils.isEmpty(number)){
            throw new IllegalArgumentException("Number cannot be empty");
        }
        return terminalDeviceDao.getTerminalDeviceByNumber(Long.parseLong(number));
    }
}
