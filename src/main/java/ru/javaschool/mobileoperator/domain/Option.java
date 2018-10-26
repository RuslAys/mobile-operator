package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "options")
public class Option extends AbstractPO {
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            mappedBy = "options")
    private Set<TariffPlan> tariffPlans;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            mappedBy = "options")
    private Set<TerminalDevice> terminalDevices;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "connection_cost")
    private Integer connectionCost;

    public Set<TariffPlan> getTariffPlans() {
        return tariffPlans;
    }

    public void setTariffPlans(Set<TariffPlan> tariffPlans) {
        this.tariffPlans = tariffPlans;
    }

    public Set<TerminalDevice> getTerminalDevices() {
        return terminalDevices;
    }

    public void setTerminalDevices(Set<TerminalDevice> terminalDevices) {
        this.terminalDevices = terminalDevices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getConnectionCost() {
        return connectionCost;
    }

    public void setConnectionCost(Integer connectionCost) {
        this.connectionCost = connectionCost;
    }
}
