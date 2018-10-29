package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "options")
public class Option extends AbstractPO {
    @ManyToMany(mappedBy = "options")
    private Set<TariffPlan> tariffPlans = new HashSet<>();

    @ManyToMany(mappedBy = "options")
    private Set<TerminalDevice> terminalDevices = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "connection_cost")
    private Integer connectionCost;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "options_to_ex_options",
            joinColumns = { @JoinColumn(name = "parent_option_id") },
            inverseJoinColumns = { @JoinColumn(name = "child_option_id") })
    private Set<Option> parentExclusive;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "options_to_in_options",
            joinColumns = { @JoinColumn(name = "parent_option_id") },
            inverseJoinColumns = { @JoinColumn(name = "child_option_id") })
    private Set<Option> parentInclusive;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            mappedBy = "parentExclusive")
    private Set<Option> exclusiveOptions;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            mappedBy = "parentInclusive")
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

    public Set<Option> getParentExclusive() {
        return parentExclusive;
    }

    public void setParentExclusive(Set<Option> parentExclusive) {
        this.parentExclusive = parentExclusive;
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

    public Set<Option> getParentInclusive() {
        return parentInclusive;
    }

    public void setParentInclusive(Set<Option> parentInclusive) {
        this.parentInclusive = parentInclusive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equals(name, option.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
