package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;

public interface CartLockService {
    void addLock(Long terminalDeviceId, Long lockId, HttpSession session, UserDetails user);
    void removeLock(Long terminalDeviceId, Long lockId, HttpSession session);
}
