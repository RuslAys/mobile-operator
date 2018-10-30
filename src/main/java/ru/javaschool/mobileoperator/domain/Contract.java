package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "contracts")
public class Contract extends AbstractPO {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    private List<PersonalAccount> personalAccounts = new ArrayList<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<PersonalAccount> getPersonalAccounts() {
        return personalAccounts;
    }

    public void setPersonalAccounts(List<PersonalAccount> personalAccounts) {
        this.personalAccounts = personalAccounts;
    }
}
