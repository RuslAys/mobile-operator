package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personal_accounts")
public class PersonalAccount extends AbstractPO {

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalAccount")
    private Set<TerminalDevice> terminalDevices = new HashSet<>();

    @Column(name = "money")
    private int money;

    public Set<TerminalDevice> getTerminalDevices() {
        return terminalDevices;
    }

    public void setTerminalDevices(Set<TerminalDevice> terminalDevices) {
        this.terminalDevices = terminalDevices;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
