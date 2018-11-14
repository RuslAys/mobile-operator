package ru.javaschool.mobileoperator.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class OptionDto {
    long id;
    private String name;
    private int price;
    private int connectionCost;
    @JsonIgnore
    private List<ContractDto> contracts = new ArrayList<>();
    @JsonIgnore
    private List<TariffPlanDto> tariffPlans = new ArrayList<>();

    public OptionDto(long id) {
        this.id = id;
    }

    public OptionDto(long id, String name, int price, int connectionCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
    }

    public OptionDto(long id, String name, int price, int connectionCost, List<ContractDto> contracts, List<TariffPlanDto> tariffPlans) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
        this.contracts = contracts;
        this.tariffPlans = tariffPlans;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getConnectionCost() {
        return connectionCost;
    }

    public void setConnectionCost(int connectionCost) {
        this.connectionCost = connectionCost;
    }

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }

    public List<TariffPlanDto> getTariffPlans() {
        return tariffPlans;
    }

    public void setTariffPlans(List<TariffPlanDto> tariffPlans) {
        this.tariffPlans = tariffPlans;
    }
}
