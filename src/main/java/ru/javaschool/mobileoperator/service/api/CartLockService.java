package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;

public interface CartLockService {
    /**
     * Method to create and add cart item with lock adding procedure to cart
     * @param terminalDeviceId terminal device id
     * @param lockId lock id to add
     * @param session http session
     * @param user user principal
     */
    void addLock(Long terminalDeviceId, Long lockId, HttpSession session, UserDetails user);

    /**
     * Method to create and add cart item with lock removing procedure to cart
     * @param terminalDeviceId terminal device id
     * @param lockId lock id to add
     * @param session http session
     * @param user user principal
     */
    void removeLock(Long terminalDeviceId, Long lockId, HttpSession session, UserDetails user);
}
