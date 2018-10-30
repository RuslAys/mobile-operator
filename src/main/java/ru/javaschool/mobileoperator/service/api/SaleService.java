package ru.javaschool.mobileoperator.service.api;

import java.util.Date;

public interface SaleService {

    void saleContract(String firstName,
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
