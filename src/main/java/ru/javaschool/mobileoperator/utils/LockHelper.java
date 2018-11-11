package ru.javaschool.mobileoperator.utils;

import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.domain.TerminalDeviceLock;

/**
 * Util class with methods for locks
 */
public class LockHelper {
    /**
     * Method to check lock can be deleted by user
     * @param terminalDevice terminal device
     * @param lock lock
     * @return boolean
     */
    public boolean canBeDeleted(TerminalDevice terminalDevice, Lock lock){
        for (TerminalDeviceLock tdl: terminalDevice.getTerminalDeviceLocks()){
            if(tdl.getLock().equals(lock)){
                return tdl.isCanBeDeletedByUser();
            }
        }
        return false;
    }

    /**
     * Method to get terminal device lock by terminal device and lock
     * @param terminalDevice terminal device
     * @param lock lock
     * @return terminal device lock
     */
    public TerminalDeviceLock getTerminalDeviceLock(TerminalDevice terminalDevice, Lock lock){
        for (TerminalDeviceLock terminalDeviceLock: terminalDevice.getTerminalDeviceLocks()){
            if(terminalDeviceLock.getLock().equals(lock)){
                return terminalDeviceLock;
            }
        }
        return null;
    }
}
