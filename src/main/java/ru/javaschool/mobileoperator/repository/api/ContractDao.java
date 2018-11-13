package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.Contract;

public interface ContractDao extends GenericDao<Contract, Long> {
    /**
     * Method to find contract with relation options
     * @param id contract id
     * @return contract with options
     */
    Contract getContractWithOptionsById(long id);

    /**
     * Method to find contract with relation option by phone number
     * @param number phone number
     * @return contract with options
     */
    Contract getContractWithOptionsByPhoneNumber(long number);
}
