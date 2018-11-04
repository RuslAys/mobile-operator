package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;

import java.util.List;

public interface ProfileService {
    TerminalDevice getFullCustomerInfoByNumber(String number);
    TariffPlan getTariffPlanOnTerminalDeviceByNumber(String number);
    List<Option> getOptionsOnTerminalDeviceByNumber(String number);
    TerminalDevice getTerminalDeviceWithLocksByNumber(String number);
    List<Lock> getLocksNotOnTerminalDevice(TerminalDevice terminalDevice);
    void addLock(Long terminalDeviceId, List<Long> lockIds);
}
