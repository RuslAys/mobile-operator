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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tariff_plans_options",
                joinColumns = { @JoinColumn(name = "tariff_id") },
                inverseJoinColumns = { @JoinColumn(name = "option_id") })
    private List<Option> options = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tariffPlan")
    private List<Contract> contracts = new ArrayList<>();

    @Column(name = "price")
    private Integer price;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "archival")
    private boolean archival;

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
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

    public boolean isArchival() {
        return archival;
    }

    public void setArchival(boolean archival) {
        this.archival = archival;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "TariffPlan:" +
                " name=" + name +
                ", price=" + price;
    }
}
