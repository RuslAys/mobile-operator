package ru.javaschool.mobileoperator.service.api;

import javax.servlet.http.HttpSession;

public interface CartOptionService {
    void addOption(Long terminalDeviceId, Long optionId, HttpSession session);
    void removeOption(Long terminalDeviceId, Long optionId, HttpSession session);
}
