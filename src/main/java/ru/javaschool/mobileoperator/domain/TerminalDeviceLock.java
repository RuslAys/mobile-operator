package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;

@Entity
@Table(name = "terminal_device_lock")
public class TerminalDeviceLock extends AbstractPO{

    @Column(name = "added_by", nullable = false)
    private String addedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "terminal_device_id")
    private TerminalDevice terminalDevice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lock_id")
    private Lock lock;

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
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
