package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.dto.ContractDto;

public interface ContractService extends GenericService<Contract, Long> {
    /**
     * Method to return contract with options
     * @param number phone number
     * @return terminal device
     */
    ContractDto getContractWithOptions(String number);
}
