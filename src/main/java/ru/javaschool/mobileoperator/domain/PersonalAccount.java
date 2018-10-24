package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERSONAL_ACCOUNT")
public class PersonalAccount extends AbstractPO {

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalAccount")
    private Set<TerminalDevice> terminalDevices;

    public Set<TerminalDevice> getTerminalDevices() {
        return terminalDevices;
    }

    public void setTerminalDevices(Set<TerminalDevice> terminalDevices) {
        this.terminalDevices = terminalDevices;
    }
}
