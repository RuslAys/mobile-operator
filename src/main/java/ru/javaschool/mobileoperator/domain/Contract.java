package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contracts")
public class Contract extends AbstractPO {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    private Set<PersonalAccount> personalAccounts = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<PersonalAccount> getPersonalAccounts() {
        return personalAccounts;
    }

    public void setPersonalAccounts(Set<PersonalAccount> personalAccounts) {
        this.personalAccounts = personalAccounts;
    }
}
