package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;

import java.util.List;

/**
 * Service to work with user profile
 */
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
     * @param user principal
     * @param terminalDeviceId terminal device id to add locks
     * @param lockId lock id to add
     */
    void addLock(UserDetails user, Long terminalDeviceId, Long lockId);

    /**
     * Method to remove lock from terminal device
     * @param user user details to check possibility to delete
     * @param terminalDeviceId terminal device id
     * @param lockId lock id to remove
     */
    void removeLock(UserDetails user, Long terminalDeviceId, Long lockId);

    /**
     * Method to get tariffs except specified
     * @param tariffPlan specified tariff plan
     * @return List of tariff plans
     */
    List<TariffPlan> getTariffsExcept(TariffPlan tariffPlan);

    /**
     * Method to change tariff plan on terminal device
     * @param terminalDeviceId terminal device id
     * @param newTariffId new tariff plan id
     */
    void changeTariff(Long terminalDeviceId, Long newTariffId);

    /**
     * Method to add option on terminal device
     * @param terminalDeviceId terminal device id
     * @param optionId option id to add
     */
    void addOption(Long terminalDeviceId, Long optionId);

    /**
     * Method to remove option from terminal device
     * @param terminalDeviceId terminal device id
     * @param optionId option id to remove
     */
    void removeOption(Long terminalDeviceId, Long optionId);
}
