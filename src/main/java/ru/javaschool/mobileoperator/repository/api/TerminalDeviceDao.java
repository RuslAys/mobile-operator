package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.TerminalDevice;

public interface TerminalDeviceDao extends GenericDao<TerminalDevice, Long> {
    TerminalDevice getFullTerminalDeviceByNumber(Long number);
}
