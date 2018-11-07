package ru.javaschool.mobileoperator.service.api;

import javax.servlet.http.HttpSession;

public interface CartTariffService {
    void changeTariffPlan(Long terminalDeviceId, Long tariffId, HttpSession session);
}
