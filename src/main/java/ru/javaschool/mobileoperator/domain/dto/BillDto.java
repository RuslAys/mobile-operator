package ru.javaschool.mobileoperator.domain.dto;

import java.util.Date;

public class BillDto {
    private long id;
    private ContractDto contractDto;
    private int balance;
    private int difference;
    private Date date;

    public BillDto() {
    }

    public BillDto(long id, int balance, int difference, Date date) {
        this.id = id;
        this.balance = balance;
        this.difference = difference;
        this.date = date;
    }

    public BillDto(long id, ContractDto contractDto, int balance, int difference, Date date) {
        this.id = id;
        this.contractDto = contractDto;
        this.balance = balance;
        this.difference = difference;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public ContractDto getContractDto() {
        return contractDto;
    }

    public void setContractDto(ContractDto contractDto) {
        this.contractDto = contractDto;
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

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}