package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.TariffPlan;

import java.util.List;

public interface TariffService extends GenericService<TariffPlan, Long> {
    void addTariff(String name, String price, List<Long> optionIds);
    TariffPlan findTariffWithOptions(Long tariffId);

    void removeOptionFromTariff(Long tariffId, Long optionId);

    void addOptionToTariff(Long tariffId, Long optionId);

    void removeTariff(Long tariffId);
}
