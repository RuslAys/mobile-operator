package ru.javaschool.mobileoperator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "bills")
public class Bill extends AbstractPO {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @ManyToOne()
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Column(name = "balance")
    private int balance;

    @Column(name = "difference")
    private int difference;

    @Column(name = "operation")
    private String operation;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
