package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.TerminalDevice;

public interface TerminalDeviceDao extends GenericDao<TerminalDevice, Long> {
    /**
     * Method to find terminal device with relation options by phone number
     * @param number phone number
     * @return terminal device
     */
    TerminalDevice getTerminalDeviceWithOptionsByNumber(Long number);

    /**
     * Method to find terminal device with relation locks by phone number
     * @param number phone number
     * @return terminal device
     */
    TerminalDevice getTerminalDeviceWithLocksByNumber(Long number);

    /**
     * Method to find terminal device with all relations
     * @param id terminal device id
     * @return terminal device
     */
    TerminalDevice getFullTerminalDeviceById(Long id);

    /**
     * Method to find terminal device with relation options by id
     * @param id terminal device id
     * @return Terminal device
     */
    TerminalDevice getTerminalDeviceWithOptionsById(Long id);
}
