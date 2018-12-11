package ru.javaschool.mobileoperator.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDto {
    private long id;
    private long number;
    private ContractDto contract;

    public PhoneNumberDto() {
    }

    public PhoneNumberDto(long id) {
        this.id = id;
    }

    public PhoneNumberDto(long id, long number) {
        this.id = id;
        this.number = number;
    }

    public PhoneNumberDto(long id, long number, ContractDto contract) {
        this.id = id;
        this.number = number;
        this.contract = contract;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public ContractDto getContract() {
        return contract;
    }

    public void setContract(ContractDto contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "PhoneNumberDto{" +
                "id=" + id +
                ", number=" + number +
                ", contract=" + contract +
                '}';
    }
}
