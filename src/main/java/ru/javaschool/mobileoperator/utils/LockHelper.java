package ru.javaschool.mobileoperator.utils;

import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.domain.TerminalDeviceLock;

public class LockHelper {
    public boolean canBeDeleted(TerminalDevice terminalDevice, Lock lock){
        for (TerminalDeviceLock tdl: terminalDevice.getTerminalDeviceLocks()){
            if(tdl.getLock().equals(lock)){
                return tdl.isCanBeDeletedByUser();
            }
        }
        return false;
    }

    public TerminalDeviceLock getTerminalDeviceLock(TerminalDevice terminalDevice, Lock lock){
        for (TerminalDeviceLock terminalDeviceLock: terminalDevice.getTerminalDeviceLocks()){
            if(terminalDeviceLock.getLock().equals(lock)){
                return terminalDeviceLock;
            }
        }
        return null;
    }
}
