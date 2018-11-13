package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Contract;

public interface ContractService extends GenericService<Contract, Long> {
    /**
     * Method to return contract with options
     * @param number phone number
     * @return terminal device
     */
    Contract getContractWithOptions(String number);
}
