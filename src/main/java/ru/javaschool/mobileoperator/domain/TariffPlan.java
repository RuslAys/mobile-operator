package ru.javaschool.mobileoperator.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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
    private List<Option> options = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tariffPlan")
    private List<TerminalDevice> terminalDevices = new ArrayList<>();

    @Column(name = "price")
    private Integer price;

    @Column(name = "name")
    private String name;

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<TerminalDevice> getTerminalDevices() {
        return terminalDevices;
    }

    public void setTerminalDevices(List<TerminalDevice> terminalDevices) {
        this.terminalDevices = terminalDevices;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TariffPlan:" +
                " name=" + name +
                ", price=" + price;
    }
}
