package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CONTRACT")
public class Contract extends AbstractPO {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    private Set<PersonalAccount> personalAccounts;

    public Set<PersonalAccount> getPersonalAccounts() {
        return personalAccounts;
    }

    public void setPersonalAccounts(Set<PersonalAccount> personalAccounts) {
        this.personalAccounts = personalAccounts;
    }
}
