package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Option;

import java.util.List;

public interface OptionService extends GenericService<Option, Long> {
    void addOption(String name,
                   List<Long> inclusiveOptions,
                   List<Long> exclusiveOptions);
    List<Option> getOptionsByIds(List<Long> ids);
}
