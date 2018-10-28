package ru.javaschool.mobileoperator.service;

import org.springframework.ui.Model;
import ru.javaschool.mobileoperator.domain.Option;

import java.util.List;
import java.util.Set;

public interface OptionService extends GenericService<Option, Long> {
    String addOption(String name,
                   List<Long> inclusiveOptions,
                   List<Long> exclusiveOptions);
    String getAll(Model model);
    List<Option> getOptionsByIds(List<Long> ids);
}
