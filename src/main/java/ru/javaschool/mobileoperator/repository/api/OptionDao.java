package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.Option;

import java.util.List;

public interface OptionDao extends GenericDao<Option, Long>{
    List<Option> getOptions();
    List<Option> getOptions(List<Long> ids);
}
