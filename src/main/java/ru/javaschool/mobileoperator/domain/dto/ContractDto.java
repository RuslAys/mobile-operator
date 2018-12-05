package ru.javaschool.mobileoperator.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ContractDto {
    private long id;
    private int balance;
    private boolean locked;
    private boolean lockedByUser;
    private CustomerDto customer;
    private PhoneNumberDto phoneNumber;
    private TariffPlanDto tariffPlan;
    private List<OptionDto> options = new ArrayList<>();

    public ContractDto() {
    }

    public ContractDto(long id, int balance, boolean locked, boolean lockedByUser, CustomerDto customer,
                       PhoneNumberDto phoneNumber, TariffPlanDto tariffPlan) {
        this.id = id;
        this.balance = balance;
        this.locked = locked;
        this.lockedByUser = lockedByUser;
        this.customer = customer;
        this.phoneNumber = phoneNumber;
        this.tariffPlan = tariffPlan;
    }

    public ContractDto(long id, int balance, boolean locked, boolean lockedByUser, CustomerDto customer,
                       PhoneNumberDto phoneNumber, TariffPlanDto tariffPlan, List<OptionDto> options) {
        this.id = id;
        this.balance = balance;
        this.locked = locked;
        this.lockedByUser = lockedByUser;
        this.customer = customer;
        this.phoneNumber = phoneNumber;
        this.tariffPlan = tariffPlan;
        this.options = options;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLockedByUser() {
        return lockedByUser;
    }

    public void setLockedByUser(boolean lockedByUser) {
        this.lockedByUser = lockedByUser;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public PhoneNumberDto getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumberDto phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TariffPlanDto getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(TariffPlanDto tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }
}
