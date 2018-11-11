package ru.javaschool.mobileoperator.service.api;

import javax.servlet.http.HttpSession;
import java.util.Date;

public interface CartSaleService {
    /**
     * Method to create and add cart item with sale procedure to cart
     * @param firstName customer first name
     * @param lastName customer last name
     * @param birthDate customer birth date
     * @param city customer address city
     * @param street customer address street
     * @param house customer address house
     * @param email customer email
     * @param passport customer passport
     * @param tariffId tariff id
     * @param numberId phone number id
     * @param httpSession http session
     */
    void sale(String firstName, String lastName, Date birthDate, String city, String street, String house, String email,
              String passport, Long tariffId, Long numberId, HttpSession httpSession);

    /**
     * Method to create and add cart item with sale to existing personal account procedure to cart
     * @param personalAccountId personal account id
     * @param tariffId tariff id
     * @param numberId number id
     * @param session http session
     */
    void saleToPersonalAccount(Long personalAccountId, Long tariffId, Long numberId, HttpSession session);
}
