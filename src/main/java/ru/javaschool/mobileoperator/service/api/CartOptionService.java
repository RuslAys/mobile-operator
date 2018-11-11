package ru.javaschool.mobileoperator.service.api;

import javax.servlet.http.HttpSession;

public interface CartOptionService {
    /**
     * Method to create and add cart item with option adding procedure to cart
     * @param terminalDeviceId terminal device id
     * @param optionId lock id to add
     * @param session http session
     */
    void addOption(Long terminalDeviceId, Long optionId, HttpSession session);

    /**
     * Method to create and add cart item with option removing procedure to cart
     * @param terminalDeviceId terminal device id
     * @param optionId lock id to add
     * @param session http session
     */
    void removeOption(Long terminalDeviceId, Long optionId, HttpSession session);
}
