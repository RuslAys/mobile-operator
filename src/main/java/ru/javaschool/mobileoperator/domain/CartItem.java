package ru.javaschool.mobileoperator.domain;

import ru.javaschool.mobileoperator.domain.enums.OperationType;

import java.util.Objects;

public class CartItem {
    private int id;
    private OperationType operationType;
    private TariffPlan tariffPlan;
    private Option option;
    private Lock lock;
    private Customer customer;
    private TerminalDevice terminalDevice;

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

    public TariffPlan getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public TerminalDevice getTerminalDevice() {
        return terminalDevice;
    }

    public void setTerminalDevice(TerminalDevice terminalDevice) {
        this.terminalDevice = terminalDevice;
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
