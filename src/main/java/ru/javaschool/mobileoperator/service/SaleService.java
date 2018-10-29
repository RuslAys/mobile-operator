package ru.javaschool.mobileoperator.service;

import org.springframework.ui.Model;

import java.util.Date;

public interface SaleService {
    String getPageWithTariffsAndNumbers(Model model);

    String saleContract(String firstName,
                        String lastName,
                        Date birthDate,
                        String city,
                        String street,
                        String house,
                        String email,
                        String passport,
                        String password,
                        String confirmPassword,
                        Long tariffId,
                        Long numberId);
}
