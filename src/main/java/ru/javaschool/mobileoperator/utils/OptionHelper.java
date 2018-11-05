package ru.javaschool.mobileoperator.utils;

import ru.javaschool.mobileoperator.domain.Option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptionHelper {
    public boolean existConflicts(List<Option> options){
        return options.stream()
                .anyMatch(option -> !Collections.disjoint(options, option.getExclusiveOptions()));
    }

    public boolean existConflicts(List<Option> left, List<Option> right){
        return left.stream()
                .anyMatch(option -> !Collections.disjoint(right, option.getExclusiveOptions()));
    }

    public boolean existConflicts(Option option, List<Option> options){
        return !Collections.disjoint(option.getExclusiveOptions(), options);
    }

    public boolean existInclusiveConflicts(List<Option> options, Option option){
        return !Collections.disjoint(option.getParentInclusive(), options);
    }

    public List<Option> getAllOptions(Option option, List<Option> list){
        list.add(option);
        if(!option.getInclusiveOptions().isEmpty()){
            for (Option option1: option.getInclusiveOptions()){
                return getAllOptions(option1, list);
            }
        }
        return list;
    }

    public List<Option> getAllOptionsWithInclusiveParents(List<Option> options, List<Option> list){
        for(Option option: options){
            if(!option.getParentInclusive().isEmpty()){
                list.add(option);
                return getAllOptionsWithInclusiveParents(option.getParentInclusive(), list);
            }
        }
        return list;
    }

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
}