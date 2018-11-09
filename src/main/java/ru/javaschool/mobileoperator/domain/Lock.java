package ru.javaschool.mobileoperator.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "locks")
public class Lock extends AbstractPO {
    @Column(name = "name")
    private String name;

    @Column(name = "delete_by_user")
    private Boolean canBeDeletedByUser;

    @OneToMany(mappedBy = "primaryKey.lock", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<TerminalDeviceLock> terminalDeviceLocks = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCanBeDeletedByUser() {
        return canBeDeletedByUser;
    }

    public void setCanBeDeletedByUser(Boolean canBeDeletedByUser) {
        this.canBeDeletedByUser = canBeDeletedByUser;
    }

    public List<TerminalDeviceLock> getTerminalDeviceLocks() {
        return terminalDeviceLocks;
    }

    public void setTerminalDeviceLocks(List<TerminalDeviceLock> terminalDeviceLocks) {
        this.terminalDeviceLocks = terminalDeviceLocks;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "name='" + name + '\'' +
                ", canBeDeletedByUser=" + canBeDeletedByUser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lock lock = (Lock) o;
        return Objects.equals(name, lock.name) &&
                Objects.equals(canBeDeletedByUser, lock.canBeDeletedByUser) &&
                Objects.equals(id, lock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, canBeDeletedByUser, id);
    }
}
