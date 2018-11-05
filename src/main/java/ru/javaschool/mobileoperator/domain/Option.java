package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "options")
public class Option extends AbstractPO {
    @ManyToMany(mappedBy = "options")
    private List<TariffPlan> tariffPlans = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "options")
    private List<TerminalDevice> terminalDevices = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "connection_cost")
    private Integer connectionCost;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "options_to_ex_options", joinColumns = { @JoinColumn(name = "parent_option_id")},
            inverseJoinColumns = { @JoinColumn(name = "child_option_id") })
    private List<Option> parentExclusive = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "options_to_in_options", joinColumns = { @JoinColumn(name = "parent_option_id") },
            inverseJoinColumns = { @JoinColumn(name = "child_option_id") })
    private List<Option> parentInclusive = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            mappedBy = "parentExclusive")
    private List<Option> exclusiveOptions = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "parentInclusive")
    private List<Option> inclusiveOptions = new ArrayList<>();

    public List<TariffPlan> getTariffPlans() {
        return tariffPlans;
    }

    public void setTariffPlans(List<TariffPlan> tariffPlans) {
        this.tariffPlans = tariffPlans;
    }

    public List<TerminalDevice> getTerminalDevices() {
        return terminalDevices;
    }

    public void setTerminalDevices(List<TerminalDevice> terminalDevices) {
        this.terminalDevices = terminalDevices;
    }

    public void setParentExclusive(List<Option> parentExclusive) {
        this.parentExclusive = parentExclusive;
    }

    public void setParentInclusive(List<Option> parentInclusive) {
        this.parentInclusive = parentInclusive;
    }

    public void setExclusiveOptions(List<Option> exclusiveOptions) {
        this.exclusiveOptions = exclusiveOptions;
    }

    public void setInclusiveOptions(List<Option> inclusiveOptions) {
        this.inclusiveOptions = inclusiveOptions;
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

    public List<Option> getParentExclusive() {
        return parentExclusive;
    }

    public List<Option> getParentInclusive() {
        return parentInclusive;
    }

    public List<Option> getExclusiveOptions() {
        return exclusiveOptions;
    }

    public List<Option> getInclusiveOptions() {
        return inclusiveOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equals(name, option.name) &&
                Objects.equals(price, option.price) && Objects.equals(id, option.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, id);
    }

    @Override
    public String toString() {
        return name;
    }
}
