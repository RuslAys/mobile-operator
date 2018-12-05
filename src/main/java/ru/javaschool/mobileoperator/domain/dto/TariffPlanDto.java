package ru.javaschool.mobileoperator.domain.dto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class TariffPlanDto {
    private long id;
    @NotNull
    private int price;
    @NotNull
    private String name;
    private boolean archival;
    private List<OptionDto> options = new ArrayList<>();

    public TariffPlanDto() {
    }

    public TariffPlanDto(long id) {
        this.id = id;
    }

    public TariffPlanDto(long id, int price, String name, boolean archival) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.archival = archival;
    }

    public TariffPlanDto(long id, int price, String name, boolean archival, List<OptionDto> options) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.archival = archival;
        this.options = options;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArchival() {
        return archival;
    }

    public void setArchival(boolean archival) {
        this.archival = archival;
    }

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }
}
