package ru.javaschool.mobileoperator.utils;

import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.enums.CartItemOperationType;

/**
 * Cart item builder to create cart item with different parameters
 */
public class CartItemBuilder {
    private int id;
    private UserDetails userDetails;
    private CartItemOperationType cartItemOperationType;
    private long tariffPlanId;
    private long optionId;
    private long lockId;
    private Customer customer;
    private long contractId;
    private long phoneNumberId;
    private boolean completed;
    private long customerId;

    private CartItemBuilder(Builder builder){
        this.id = builder.id;
        this.userDetails = builder.userDetails;
        this.cartItemOperationType = builder.cartItemOperationType;
        this.tariffPlanId = builder.tariffPlanId;
        this.optionId = builder.optionId;
        this.lockId = builder.lockId;
        this.customer = builder.customer;
        this.contractId = builder.contractId;
        this.phoneNumberId = builder.phoneNumberId;
        this.completed = builder.completed;
        this.customerId = builder.customerId;
    }

    public static class Builder{
        private int id;
        private UserDetails userDetails;
        private CartItemOperationType cartItemOperationType;
        private long tariffPlanId;
        private long optionId;
        private long lockId;
        private Customer customer;
        private long contractId;
        private long phoneNumberId;
        private boolean completed;
        private long customerId;

        public Builder(int id, CartItemOperationType cartItemOperationType){
            this.id = id;
            this.cartItemOperationType = cartItemOperationType;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserDetails(UserDetails userDetails) {
            this.userDetails = userDetails;
            return this;
        }

        public Builder setCartItemOperationType(CartItemOperationType cartItemOperationType) {
            this.cartItemOperationType = cartItemOperationType;
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

        public Builder setContractId(long contractId) {
            this.contractId = contractId;
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

        public Builder setCustomerId(long customerId){
            this.customerId = customerId;
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

    public CartItemOperationType getCartItemOperationType() {
        return cartItemOperationType;
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

    public long getContractId() {
        return contractId;
    }

    public long getPhoneNumberId() {
        return phoneNumberId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public long getCustomerId() {
        return customerId;
    }
}
