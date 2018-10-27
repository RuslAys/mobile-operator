package ru.javaschool.mobileoperator.repository;

import ru.javaschool.mobileoperator.domain.Option;

import java.util.List;
import java.util.Set;

public interface OptionDao extends GenericDao<Option, Long>{
    List<Option> getOptions();
    List<Option> getOptions(List<Long> ids);
}
