package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;

import java.util.List;

public interface OptionDao extends GenericDao<Option, Long>{
    /**
     * Method to find all options
     * @return list with options
     */
    List<Option> getOptions();

    /**
     * Method to find all options by specified options ids
     * @param ids specified options ids
     * @return list with options
     */
    List<Option> getOptions(List<Long> ids);

    /**
     * Method to find options on terminal device by number
     * @param number phone number
     * @return list with options
     */
    List<Option> getOptionsOnContractByNumber(Long number);

    /**
     * Method to find options on tariff plan
     * @param tariffPlan tariff plan
     * @return list with options
     */
    List<Option> getOptionsOnTariffPlan(TariffPlan tariffPlan);

    /**
     * Method to find options except specified options
     * @param options specified options
     * @return list with options
     */
    List<Option> getOptionsNotIn(List<Option> options);

    /**
     * Method to find option with all dependencies by id
     * @param id option id
     * @return option
     */
    Option getFullOptionById(Long id);
}
