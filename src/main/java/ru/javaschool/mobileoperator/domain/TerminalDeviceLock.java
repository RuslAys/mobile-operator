package ru.javaschool.mobileoperator.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "terminal_device_lock")
public class TerminalDeviceLock extends AbstractPO{

    @Column(name = "added_by", nullable = false)
    private boolean canBeDeletedByUser;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "terminal_device_id")
    private TerminalDevice terminalDevice;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "lock_id")
    private Lock lock;

    public boolean isCanBeDeletedByUser() {
        return canBeDeletedByUser;
    }

    public void setCanBeDeletedByUser(boolean canBeDeletedByUser) {
        this.canBeDeletedByUser = canBeDeletedByUser;
    }

    public TerminalDevice getTerminalDevice() {
        return terminalDevice;
    }

    public void setTerminalDevice(TerminalDevice terminalDevice) {
        this.terminalDevice = terminalDevice;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
