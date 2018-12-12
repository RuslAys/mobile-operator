package ru.javaschool.mobileoperator.domain;

import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.enums.CartItemOperationType;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

import java.util.Objects;

public class CartItem {
    private int id;
    private CartItemOperationType cartItemOperationType;
    private String title;
    private long tariffPlanId;
    private long optionId;
    private long lockId;
    private Customer customer;
    private long contractId;
    private long phoneNumberId;
    private boolean completed;
    private UserDetails user;
    private long customerId;

    public CartItem(CartItemBuilder builder){
        this.id = builder.getId();
        this.cartItemOperationType = builder.getCartItemOperationType();
        this.tariffPlanId = builder.getTariffPlanId();
        this.optionId = builder.getOptionId();
        this.lockId = builder.getLockId();
        this.customer = builder.getCustomer();
        this.contractId = builder.getContractId();
        this.phoneNumberId = builder.getPhoneNumberId();
        this.completed = builder.isCompleted();
        this.user = builder.getUserDetails();
        this.customerId = builder.getCustomerId();
        this.title = builder.getTitle();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CartItemOperationType getCartItemOperationType() {
        return cartItemOperationType;
    }

    public void setCartItemOperationType(CartItemOperationType cartItemOperationType) {
        this.cartItemOperationType = cartItemOperationType;
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

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id &&
                cartItemOperationType == cartItem.cartItemOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartItemOperationType);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cartItemOperationType=" + cartItemOperationType +
                '}';
    }
}
