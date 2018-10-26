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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Option optionWithReferences;

    @OneToMany(mappedBy = "optionWithReferences")
    private Set<Option> exclusiveOptions;

    @OneToMany(mappedBy = "optionWithReferences")
    private Set<Option> inclusiveOptions;

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

    public Option getOptionWithReferences() {
        return optionWithReferences;
    }

    public void setOptionWithReferences(Option optionWithReferences) {
        this.optionWithReferences = optionWithReferences;
    }

    public Set<Option> getExclusiveOptions() {
        return exclusiveOptions;
    }

    public void setExclusiveOptions(Set<Option> exclusiveOptions) {
        this.exclusiveOptions = exclusiveOptions;
    }

    public Set<Option> getInclusiveOptions() {
        return inclusiveOptions;
    }

    public void setInclusiveOptions(Set<Option> inclusiveOptions) {
        this.inclusiveOptions = inclusiveOptions;
    }
}
