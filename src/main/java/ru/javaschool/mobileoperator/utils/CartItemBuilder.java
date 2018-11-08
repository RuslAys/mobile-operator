package ru.javaschool.mobileoperator.utils;

import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.PhoneNumber;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.domain.enums.OperationType;

public class CartItemBuilder {
    private int id;
    private UserDetails userDetails;
    private OperationType operationType;
    private long tariffPlanId;
    private long optionId;
    private long lockId;
    private Customer customer;
    private long terminalDeviceId;
    private long phoneNumberId;
    private boolean completed;

    private CartItemBuilder(Builder builder){
        this.id = builder.id;
        this.userDetails = builder.userDetails;
        this.operationType = builder.operationType;
        this.tariffPlanId = builder.tariffPlanId;
        this.optionId = builder.optionId;
        this.lockId = builder.lockId;
        this.customer = builder.customer;
        this.terminalDeviceId = builder.terminalDeviceId;
        this.phoneNumberId = builder.phoneNumberId;
        this.completed = builder.completed;
    }

    public static class Builder{
        private int id;
        private UserDetails userDetails;
        private OperationType operationType;
        private long tariffPlanId;
        private long optionId;
        private long lockId;
        private Customer customer;
        private long terminalDeviceId;
        private long phoneNumberId;
        private boolean completed;

        public Builder(int id, OperationType operationType){
            this.id = id;
            this.operationType = operationType;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserDetails(UserDetails userDetails) {
            this.userDetails = userDetails;
            return this;
        }

        public Builder setOperationType(OperationType operationType) {
            this.operationType = operationType;
            return this;
        }

        public Builder setTariffPlanId(long tariffPlanId) {
            this.tariffPlanId = tariffPlanId;
            return this;
        }

        public Builder setOptionId(long optionId) {
            this.optionId = optionId;
            return this;
        }

        public Builder setLockId(long lockId) {
            this.lockId = lockId;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setTerminalDeviceId(long terminalDeviceId) {
            this.terminalDeviceId = terminalDeviceId;
            return this;
        }

        public Builder setPhoneNumberId(long phoneNumberId) {
            this.phoneNumberId = phoneNumberId;
            return this;
        }

        public Builder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public CartItemBuilder build(){
            return new CartItemBuilder(this);
        }
    }

    public int getId() {
        return id;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public long getTariffPlanId() {
        return tariffPlanId;
    }

    public long getOptionId() {
        return optionId;
    }

    public long getLockId() {
        return lockId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getTerminalDeviceId() {
        return terminalDeviceId;
    }

    public long getPhoneNumberId() {
        return phoneNumberId;
    }

    public boolean isCompleted() {
        return completed;
    }
}
