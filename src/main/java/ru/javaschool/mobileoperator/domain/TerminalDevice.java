package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "terminal_devices")
public class TerminalDevice extends AbstractPO {
    @ManyToOne
    @JoinColumn(name = "personal_account_id")
    private PersonalAccount personalAccount;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderColumn
    @JoinTable(name = "terminal_devices_options", joinColumns = { @JoinColumn(name = "terminal_device_id") },
            inverseJoinColumns = { @JoinColumn(name = "option_id") })
    private List<Option> options = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tariff_plan_id")
    private TariffPlan tariffPlan;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "terminal_device_phone_number",
            joinColumns = @JoinColumn(name = "terminal_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_number_id"))
    private PhoneNumber phoneNumber;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "terminal_devices_locks", joinColumns = { @JoinColumn(name = "terminal_device_id") },
            inverseJoinColumns = { @JoinColumn(name = "lock_id") })
    private List<Lock> locks = new ArrayList<>();

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Lock> getLocks() {
        return locks;
    }

    public void setLocks(List<Lock> locks) {
        this.locks = locks;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerminalDevice that = (TerminalDevice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
