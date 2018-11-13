package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Option;

import java.util.List;

public interface OptionService extends GenericService<Option, Long> {
    /**
     * Method for add new option to data base
     * @param name option name
     * @param price option price
     * @param connectionCost option connection cost
     * @param inclusiveOptions inclusive options ids
     * @param exclusiveOptions exclusive options ids
     */
    void createOption(String name,
                      String price,
                      String connectionCost,
                      List<Long> inclusiveOptions,
                      List<Long> exclusiveOptions);

    /**
     * Method for find options by ids
     * @param ids options ids
     * @return list with options
     */
    List<Option> getOptionsByIds(List<Long> ids);

    /**
     * Method for find options not like specified options
     * @param options specified options
     * @return list with options
     */
    List<Option> getOptionsNotIn(List<Option> options);

    /**
     * Method for find option with all relations
     * @param id options id
     * @return option
     */
    Option getFullOptionById(Long id);

    /**
     * Method to add option on terminal device
     * @param contractId terminal device id
     * @param optionId option id to add
     */
    void addOptionToContract(Long contractId, Long optionId);

    /**
     * Method to remove option from terminal device
     * @param contractId terminal device
     * @param optionId option to remove
     */
    void removeOptionFromContract(Long contractId, Long optionId);

    /**
     * Method to return options on contract by phone number
     * @param number phone number
     * @return list with options
     */
    List<Option> getOptionsOnContractByNumber(String number);
}
