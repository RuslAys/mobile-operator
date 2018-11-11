package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Lock;

public interface LockService extends GenericService<Lock, Long> {
    /**
     * Method for add new lock to data base
     * @param name lock name
     */
    void addLock(String name);
    /**
     * Method to add locks on terminal device
     * @param user principal
     * @param terminalDeviceId terminal device id to add locks
     * @param lockId lock id to add
     */
    void addLock(UserDetails user, Long terminalDeviceId, Long lockId);

    /**
     * Method to remove lock from terminal device
     * @param user user details to check possibility to delete
     * @param terminalDeviceId terminal device id
     * @param lockId lock id to remove
     */
    void removeLock(UserDetails user, Long terminalDeviceId, Long lockId);
}
