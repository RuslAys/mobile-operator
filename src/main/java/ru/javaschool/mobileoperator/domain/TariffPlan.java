package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tariff_plans")
public class TariffPlan extends AbstractPO {

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "tariff_plans_options",
                joinColumns = { @JoinColumn(name = "tariff_id") },
                inverseJoinColumns = { @JoinColumn(name = "option_id") })
    private Set<Option> options;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tariffPlan")
    private Set<TerminalDevice> terminalDevices;

    @Column(name = "price")
    private Integer price;

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Set<TerminalDevice> getTerminalDevices() {
        return terminalDevices;
    }

    public void setTerminalDevices(Set<TerminalDevice> terminalDevices) {
        this.terminalDevices = terminalDevices;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
