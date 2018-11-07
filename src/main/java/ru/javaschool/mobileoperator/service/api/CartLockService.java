package ru.javaschool.mobileoperator.service.api;

import javax.servlet.http.HttpSession;

public interface CartLockService {
    void addLock(Long terminalDeviceId, Long lockId, HttpSession session);
    void removeLock(Long terminalDeviceId, Long lockId, HttpSession session);
}
