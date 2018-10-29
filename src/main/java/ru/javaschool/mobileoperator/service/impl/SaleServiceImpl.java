package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.service.PhoneNumberService;
import ru.javaschool.mobileoperator.service.SaleService;
import ru.javaschool.mobileoperator.service.TariffService;

@Service("saleContractService")
public class SaleServiceImpl implements SaleService {
    @Autowired
    TariffService tariffService;

    @Autowired
    PhoneNumberService phoneNumberService;
}
