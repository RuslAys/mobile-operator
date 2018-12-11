package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.dto.TariffPlanDto;
import ru.javaschool.mobileoperator.service.api.JmsMessageSender;
import ru.javaschool.mobileoperator.service.api.TariffJmsService;
import ru.javaschool.mobileoperator.service.api.TariffService;

import java.util.List;

@Service("tariffJmsService")
public class TariffJmsServiceImpl implements TariffJmsService {

    private static final String MESSAGE_TEXT = "updated";

    @Autowired
    private TariffService tariffService;

    @Autowired
    private JmsMessageSender jmsMessageSender;

    @Override
    public void addTariff(TariffPlanDto tariffPlanDto, List<Long> optionIds) {
        tariffService.addTariff(tariffPlanDto, optionIds);
        jmsMessageSender.send(MESSAGE_TEXT);
    }

    @Override
    public void removeOptionFromTariff(Long tariffId, Long optionId) {
        tariffService.removeOptionFromTariff(tariffId, optionId);
        jmsMessageSender.send(MESSAGE_TEXT);
    }

    @Override
    public void addOptionToTariff(Long tariffId, Long optionId) {
        tariffService.addOptionToTariff(tariffId, optionId);
        jmsMessageSender.send(MESSAGE_TEXT);
    }

    @Override
    public void removeTariff(Long tariffId) {
        tariffService.removeTariff(tariffId);
        jmsMessageSender.send(MESSAGE_TEXT);
    }
}
