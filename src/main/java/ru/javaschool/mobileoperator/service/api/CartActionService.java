package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Cart;

import javax.servlet.http.HttpSession;

public interface CartActionService {
    /**
     * Method to create and add cart item with change tariff procedure to cart
     * @param contractId contract id
     * @param tariffId new tariff id
     */
    void changeTariffPlan(Cart cart, Long contractId, Long tariffId);

    /**
     * Method to create and add cart item with option adding procedure to cart
     * @param contractId terminal device id
     * @param optionId lock id to add
     * @param cart session cart
     */
    void addOption(Cart cart, Long contractId, Long optionId);

    /**
     * Method to create and add cart item with option removing procedure to cart
     * @param contractId terminal device id
     * @param optionId lock id to add
     * @param cart session cart
     */
    void removeOption(Cart cart, Long contractId, Long optionId);

    /**
     * Method to create and add cart item with contract locking procedure to cart
     * @param cart session
     * @param contractId
     * @param userDetails
     */
    void lockContract(Cart cart, Long contractId, UserDetails userDetails);
}
