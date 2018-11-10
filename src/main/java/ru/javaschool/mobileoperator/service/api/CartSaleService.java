package ru.javaschool.mobileoperator.service.api;

import javax.servlet.http.HttpSession;
import java.util.Date;

public interface CartSaleService {
    void sale(String firstName, String lastName, Date birthDate, String city, String street, String house, String email,
              String passport, Long tariffId, Long numberId, HttpSession httpSession);

    void saleToPersonalAccount(Long personalAccountId, Long tariffId, Long numberId, HttpSession session);
}
