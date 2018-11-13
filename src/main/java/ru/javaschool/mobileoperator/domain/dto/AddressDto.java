package ru.javaschool.mobileoperator.domain.dto;

public class AddressDto {
    private String city;
    private String street;
    private String houseNumber;

    public AddressDto() {
    }

    public AddressDto(String city, String street, String houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
