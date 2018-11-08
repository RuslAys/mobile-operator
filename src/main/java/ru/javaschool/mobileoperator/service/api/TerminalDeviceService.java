package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.TerminalDevice;

public interface TerminalDeviceService extends GenericService<TerminalDevice, Long> {
    TerminalDevice getFullTerminalDevice(Long id);
}
