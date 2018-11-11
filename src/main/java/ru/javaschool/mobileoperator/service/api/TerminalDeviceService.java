package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.TerminalDevice;

public interface TerminalDeviceService extends GenericService<TerminalDevice, Long> {
    /**
     * Method to find terminal device with all relations
     * @param id terminal device id
     * @return terminal device
     */
    TerminalDevice getFullTerminalDevice(Long id);
}
