package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.TerminalDevice;

public interface ProfileService {
    TerminalDevice getFullCustomerInfoByNumber(String number);
}
