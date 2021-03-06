package ru.javaschool.mobileoperator.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Util class with methods for options
 */
@Component
@Scope("singleton")
public class OptionHelper {
    /**
     * Method to check conflicts with exclusive options in specified list
     * @param options list with option
     * @return boolean
     */
    public boolean existConflicts(List<Option> options){
        return options.stream()
                .anyMatch(option -> !Collections.disjoint(options, option.getExclusiveOptions()));
    }

    /**
     * Method to check conflicts with exclusive options in specified lists
     * @param left list with options to check
     * @param right list with possible exclusive options
     * @return true if right list contains exclusive options for left, opposite false
     */
    public boolean existConflicts(List<Option> left, List<Option> right){
        return left.stream()
                .anyMatch(option -> !Collections.disjoint(right, option.getExclusiveOptions()));
    }

    /**
     * Method to check conflicts with exclusive options in specified option
     * @param option option to check
     * @param options list with possible exclusive options
     * @return true if list contains exclusive options with specified option, opposite false
     */
    public boolean existConflicts(Option option, List<Option> options){
        return !Collections.disjoint(option.getExclusiveOptions(), options);
    }

    /**
     * Method to find inclusive options for specified option
     * @param options list with options
     * @param option specified option
     * @return true if list contains inclusive options with specified option, opposite false
     */
    public boolean existInclusiveConflicts(List<Option> options, Option option){
        return !Collections.disjoint(option.getParentInclusive(), options);
    }

    /**
     * Get all inclusive option to list
     * @param option option with possible inclusive options
     * @param list list to fill
     * @return list with all inclusive options
     */
    public List<Option> getAllOptions(Option option, List<Option> list){
        list.add(option);
        if(!option.getInclusiveOptions().isEmpty()){
            for (Option option1: option.getInclusiveOptions()){
                return getAllOptions(option1, list);
            }
        }
        return list;
    }

    /**
     * Method to get all parent inclusive options
     * @param options list with options
     * @param list list to fill
     * @return filled list with all parent inclusive options
     */
    public List<Option> getAllOptionsWithInclusiveParents(List<Option> options, List<Option> list){
        for(Option option: options){
            list.add(option);
            if(!option.getParentInclusive().isEmpty()){
                return getAllOptionsWithInclusiveParents(option.getParentInclusive(), list);
            }
        }
        return list;
    }

    /**
     * Method to get all exclusive options
     * @param mainOptions options for looking
     * @param conflicts possible conflict
     * @return list with all exclusive options
     */
    public List<Option> getExclusiveOptions(List<Option> mainOptions, List<Option> conflicts){
        List<Option> result = new ArrayList<>();
        mainOptions.forEach(
                option -> {
                    if(existConflicts(option, conflicts)){
                        result.add(option);
                    }
                }
        );
        return result;
    }

    /**
     * Method to remove list with options from terminal device
     * @param contract terminal device
     * @param options list with options
     */
    public void removeOptionsFromContract(Contract contract, List<Option> options){
        contract.getOptions().removeAll(options);
        options.forEach(
                option -> option.getContracts().removeIf(
                        contract1 -> contract.equals(contract1)
                )
        );
    }

    /**
     * Method to remove option from terminal device
     * @param contract terminal device
     * @param option option
     */
    public void removeOptionFromContract(Contract contract, Option option){
        contract.getOptions().removeIf(option1 -> option.equals(option1));
        option.getContracts().removeIf(contract1 -> contract.equals(contract1));
    }

    /**
     * Method to remove option from tariff plan
     * @param tariffPlan tariff plan
     * @param option option
     */
    public void removeOptionFromTariff(TariffPlan tariffPlan, Option option){
        tariffPlan.getOptions().removeIf(option1 -> option.equals(option1));
        option.getTariffPlans().removeIf(tariffPlan1 -> tariffPlan.equals(tariffPlan1));
    }

    /**
     * Method to remove list with options from tariff plan
     * @param tariffPlan tariff plan
     * @param options list with options
     */
    public void removeOptionsFromTariff(TariffPlan tariffPlan, List<Option> options){
        tariffPlan.getOptions().removeAll(options);
        options.forEach(
                option -> option.getTariffPlans().removeIf(
                        tariffPlan1 -> tariffPlan.equals(tariffPlan1)
                )
        );
    }

    /**
     * Method to get set with all inclusive options
     * @param option option to bypass
     * @param options set with result options to bypass
     * @return set with options
     */
    public Set<Option> getInclusiveOptionsToAdd(Option option, Set<Option> options){
        options.add(option);
        if(option.getInclusiveOptions() != null && !option.getInclusiveOptions().isEmpty()){
            for (Option option1: option.getInclusiveOptions()){
                if(!options.contains(option1)){
                    return getInclusiveOptionsToAdd(option1, options);
                }
            }
        }
        return options;
    }

    public Set<Option> getParentInclusiveOptionsOnContract(Contract contract, List<Option> options){
        Set<Option> uniqueOptions = new HashSet<>();
        options.forEach(option -> uniqueOptions.addAll(option.getParentInclusive()));
        if(uniqueOptions.isEmpty()){
            return new HashSet<>();
        }
        uniqueOptions.removeIf(option -> !contract.getOptions().contains(option));
        return uniqueOptions;
    }

    public Set<Option> getExclusiveOptionsOnContract(Contract contract, List<Option> options){
        Set<Option> uniqueOptions = new HashSet<>();
        options.forEach(option -> uniqueOptions.addAll(option.getParentExclusive()));
        if(uniqueOptions.isEmpty()){
            return new HashSet<>();
        }
        uniqueOptions.removeIf(option -> !contract.getOptions().contains(option));
        Set<Option> result = getParentInclusiveOptionsOnContract(contract, new ArrayList<>(uniqueOptions));
        result.addAll(uniqueOptions);
        return result;
    }

    public Set<Option> getChildInclusiveOptionsOnContract(Contract contract, List<Option> options){
        Set<Option> uniqueOptions = new HashSet<>();
        options.forEach(option -> uniqueOptions.addAll(option.getInclusiveOptions()));
        if(uniqueOptions.isEmpty()){
            return new HashSet<>();
        }
        uniqueOptions.removeIf(option -> contract.getOptions().contains(option));
        return uniqueOptions;
    }

    public Set<Option> getExclusiveOptionsOnTariffPlan(TariffPlan tariffPlan, List<Option> options){
        Set<Option> uniqueOptions = new HashSet<>();
        options.forEach(option -> uniqueOptions.addAll(option.getParentExclusive()));
        if(uniqueOptions.isEmpty()){
            return new HashSet<>();
        }
        uniqueOptions.removeIf(option -> !tariffPlan.getOptions().contains(option));
        Set<Option> result = getParentInclusiveOptionsOnTariffPlan(tariffPlan, new ArrayList<>(uniqueOptions));
        result.addAll(uniqueOptions);
        return result;
    }

    public Set<Option> getParentInclusiveOptionsOnTariffPlan(TariffPlan tariffPlan, List<Option> options){
        Set<Option> uniqueOptions = new HashSet<>();
        options.forEach(option -> uniqueOptions.addAll(option.getParentInclusive()));
        if(uniqueOptions.isEmpty()){
            return new HashSet<>();
        }
        uniqueOptions.removeIf(option -> !tariffPlan.getOptions().contains(option));
        return uniqueOptions;
    }

    public Set<Option> getChildInclusiveOptionsOnTariffPlan(TariffPlan tariffPlan, List<Option> options){
        Set<Option> uniqueOptions = new HashSet<>();
        options.forEach(option -> uniqueOptions.addAll(option.getInclusiveOptions()));
        if(uniqueOptions.isEmpty()){
            return new HashSet<>();
        }
        uniqueOptions.removeIf(option -> tariffPlan.getOptions().contains(option));
        return uniqueOptions;
    }
}
