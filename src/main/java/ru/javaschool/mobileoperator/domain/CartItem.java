package ru.javaschool.mobileoperator.domain;

import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

import java.util.Objects;

public class CartItem {
    private int id;
    private OperationType operationType;
    private long tariffPlanId;
    private long optionId;
    private long lockId;
    private Customer customer;
    private long terminalDeviceId;
    private long phoneNumberId;
    private boolean completed;
    private UserDetails user;

    public CartItem(CartItemBuilder builder){
        this.id = builder.getId();
        this.operationType = builder.getOperationType();
        this.tariffPlanId = builder.getTariffPlanId();
        this.optionId = builder.getOptionId();
        this.lockId = builder.getLockId();
        this.customer = builder.getCustomer();
        this.terminalDeviceId = builder.getTerminalDeviceId();
        this.phoneNumberId = builder.getPhoneNumberId();
        this.completed = builder.isCompleted();
        this.user = builder.getUserDetails();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public long getTariffPlanId() {
        return tariffPlanId;
    }

    public void setTariffPlanId(long tariffPlanId) {
        this.tariffPlanId = tariffPlanId;
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public long getLockId() {
        return lockId;
    }

    public void setLockId(long lockId) {
        this.lockId = lockId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getTerminalDeviceId() {
        return terminalDeviceId;
    }

    public void setTerminalDeviceId(long terminalDeviceId) {
        this.terminalDeviceId = terminalDeviceId;
    }

    public long getPhoneNumberId() {
        return phoneNumberId;
    }

    public void setPhoneNumberId(long phoneNumberId) {
        this.phoneNumberId = phoneNumberId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id &&
                operationType == cartItem.operationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operationType);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", operationType=" + operationType +
                '}';
    }
}
