package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "terminal_devices")
public class TerminalDevice extends AbstractPO {
    @ManyToOne
    @JoinColumn(name = "personal_account_id")
    private PersonalAccount personalAccount;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "terminal_devices_options",
            joinColumns = { @JoinColumn(name = "terminal_device_id") },
            inverseJoinColumns = { @JoinColumn(name = "option_id") })
    private Set<Option> options;

    @ManyToOne
    @JoinColumn(name = "tariff_plan_id")
    private TariffPlan tariffPlan;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "phone_number_id")
    private PhoneNumber phoneNumber;

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public TariffPlan getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
