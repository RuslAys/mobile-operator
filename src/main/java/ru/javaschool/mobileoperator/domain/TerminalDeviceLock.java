package ru.javaschool.mobileoperator.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
@Table(name = "terminal_devices_locks")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.terminalDevice",
                joinColumns = @JoinColumn(name = "terminal_device_id")),
        @AssociationOverride(name = "primaryKey.lock",
                joinColumns = @JoinColumn(name = "lock_id")) })
public class TerminalDeviceLock{

    @EmbeddedId
    private TerminalDeviceLockId primaryKey = new TerminalDeviceLockId();

    @Column(name = "can_be_deleted_by_user", nullable = false)
    private boolean canBeDeletedByUser;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "terminal_device_id")
    @Transient
    private TerminalDevice terminalDevice;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "lock_id")
    @Transient
    private Lock lock;

    public boolean isCanBeDeletedByUser() {
        return canBeDeletedByUser;
    }

    public void setCanBeDeletedByUser(boolean canBeDeletedByUser) {
        this.canBeDeletedByUser = canBeDeletedByUser;
    }

    public TerminalDevice getTerminalDevice() {
        return primaryKey.getTerminalDevice();
    }

    public void setTerminalDevice(TerminalDevice terminalDevice) {
        primaryKey.setTerminalDevice(terminalDevice);
    }

    public Lock getLock() {
        return primaryKey.getLock();
    }

    public void setLock(Lock lock) {
        primaryKey.setLock(lock);
    }

    public TerminalDeviceLockId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(TerminalDeviceLockId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerminalDeviceLock that = (TerminalDeviceLock) o;
        return canBeDeletedByUser == that.canBeDeletedByUser &&
                Objects.equals(primaryKey, that.primaryKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryKey, canBeDeletedByUser);
    }
}
