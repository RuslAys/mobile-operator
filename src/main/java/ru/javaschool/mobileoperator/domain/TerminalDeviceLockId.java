package ru.javaschool.mobileoperator.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TerminalDeviceLockId implements Serializable {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private TerminalDevice terminalDevice;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Lock lock;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerminalDeviceLockId that = (TerminalDeviceLockId) o;
        return Objects.equals(terminalDevice, that.terminalDevice) &&
                Objects.equals(lock, that.lock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(terminalDevice, lock);
    }
}
