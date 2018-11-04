package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;

import java.util.List;

public interface ProfileService {
    /**
     * Method to return all customer information by number
     * @param number phone number
     * @return terminal device
     */
    TerminalDevice getFullCustomerInfoByNumber(String number);

    /**
     * Method to return tariff plan on terminal device by phone number
     * @param number phone number
     * @return tariff plan
     */
    TariffPlan getTariffPlanOnTerminalDeviceByNumber(String number);

    /**
     * Method to return options on terminal device by phone number
     * @param number phone number
     * @return list with options
     */
    List<Option> getOptionsOnTerminalDeviceByNumber(String number);

    /**
     * Method to return terminal device with only locks by phone number
     * @param number phone number
     * @return terminal device
     */
    TerminalDevice getTerminalDeviceWithLocksByNumber(String number);

    /**
     * Method to return locks on terminal device by phone number
     * @param terminalDevice terminal device to find
     * @return list with locks
     */
    List<Lock> getLocksNotOnTerminalDevice(TerminalDevice terminalDevice);

    /**
     * Method to add locks on terminal device
     * @param terminalDeviceId terminal device id to add locks
     * @param lockIds list with locks to add
     */
    void addLock(Long terminalDeviceId, List<Long> lockIds);

    /**
     * Method to remove lock from terminal device
     * @param user user details to check possibility to delete
     * @param terminalDeviceId terminal device id
     * @param lockId lock id to remove
     */
    void removeLock(UserDetails user, Long terminalDeviceId, Long lockId);
}
