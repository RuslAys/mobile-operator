package ru.javaschool.mobileoperator.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDto {
    private long id;
    private long number;
    private List<ContractDto> contracts = new ArrayList<>();

    public PhoneNumberDto(long id) {
        this.id = id;
    }

    public PhoneNumberDto(long id, long number) {
        this.id = id;
        this.number = number;
    }

    public PhoneNumberDto(long id, long number, List<ContractDto> contracts) {
        this.id = id;
        this.number = number;
        this.contracts = contracts;
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

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }
}
