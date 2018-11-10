package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TerminalDevice;

import java.util.List;

public interface OptionService extends GenericService<Option, Long> {
    void addOption(String name,
                   String price,
                   String connectionCost,
                   List<Long> inclusiveOptions,
                   List<Long> exclusiveOptions);
    List<Option> getOptionsByIds(List<Long> ids);

    List<Option> getOptionsNotIn(List<Option> options);

    Option getFullOptionById(Long id);

    /**
     * Method to add option on terminal device
     * @param terminalDeviceId terminal device id
     * @param optionId option id to add
     */
    void addOptionToTerminalDevice(Long terminalDeviceId, Long optionId);

    /**
     * Method to remove option from terminal device
     * @param terminalDeviceId terminal device
     * @param optionId option to remove
     */
    void removeOptionFromTerminalDevice(Long terminalDeviceId, Long optionId);
}
