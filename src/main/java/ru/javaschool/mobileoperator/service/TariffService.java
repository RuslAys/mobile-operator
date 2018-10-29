package ru.javaschool.mobileoperator.service;

import org.springframework.ui.Model;
import ru.javaschool.mobileoperator.domain.TariffPlan;

import java.util.List;

public interface TariffService extends GenericService<TariffPlan, Long> {
    void addTariff(String name, String price, List<Long> optionIds);
}
