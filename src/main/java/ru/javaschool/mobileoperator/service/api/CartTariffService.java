package ru.javaschool.mobileoperator.service.api;

import javax.servlet.http.HttpSession;

public interface CartTariffService {
    /**
     * Method to create and add cart item with change tariff procedure to cart
     * @param terminalDeviceId terminal device id
     * @param tariffId new tariff id
     * @param session http session
     */
    void changeTariffPlan(Long terminalDeviceId, Long tariffId, HttpSession session);
}
