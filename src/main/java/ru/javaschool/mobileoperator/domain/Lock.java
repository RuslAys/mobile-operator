package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "locks")
public class Lock extends AbstractPO {
    @Column(name = "name")
    private String name;

    @Column(name = "delete_by_user")
    private Boolean canBeDeletedByUser;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "locks")
    private List<TerminalDevice> terminalDevices = new ArrayList<>();

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

    public List<TerminalDevice> getTerminalDevices() {
        return terminalDevices;
    }

    public void setTerminalDevices(List<TerminalDevice> terminalDevices) {
        this.terminalDevices = terminalDevices;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "name='" + name + '\'' +
                ", canBeDeletedByUser=" + canBeDeletedByUser +
                '}';
    }
}
