package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.dto.OptionDto;

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
    List<OptionDto> getOptionsByIds(List<Long> ids);

    /**
     * Method for find options not like specified options
     * @param options specified options
     * @return list with options
     */
    List<OptionDto> getOptionsNotIn(List<OptionDto> options);

    /**
     * Method for find option with all relations
     * @param id options id
     * @return option
     */
    OptionDto getFullOptionById(Long id);

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
    List<OptionDto> getOptionsOnContractByNumber(String number);

    /**
     * Method to return all options
     * @return list with options
     */
    List<OptionDto> getAllOptionsWithoutLists();

    /**
     * Method to return option by contract without relations
     * @param id option id
     * @return option
     */
    OptionDto getOptionWithoutLists(long id);

    /**
     * Method to return parent inclusive options on contract
     * @param contractId contract id
     * @param optionIds option ids
     * @return list with options
     */
    List<OptionDto> getParentInclusiveOptionsOnContract(long contractId, List<Long> optionIds);

    /**
     * Method to return exclusive options on contract
     * @param contractId contract id
     * @param optionIds option ids
     * @return list with options
     */
    List<OptionDto> getExclusiveOptionsOnContract(long contractId, List<Long> optionIds);

    /**
     * Method to return child inclusive options on contract
     * @param contractId contract id
     * @param optionIds option ids
     * @return list with options
     */
    List<OptionDto> getChildInclusiveOptionsOnContract(long contractId, List<Long> optionIds);

    /**
     * Method to find option with lists by id
     * @param optionId option id
     * @return option dto
     */
    OptionDto findOptionWithListsById(Long optionId);
}
