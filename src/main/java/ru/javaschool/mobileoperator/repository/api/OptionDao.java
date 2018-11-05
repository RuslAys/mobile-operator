package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;

import java.util.List;

public interface OptionDao extends GenericDao<Option, Long>{
    List<Option> getOptions();
    List<Option> getOptions(List<Long> ids);
    List<Option> getOptionsByNumber(Long number);

    List<Option> getOptions(TariffPlan tariffPlan);

    List<Option> getOptionsNotIn(List<Option> options);
}
