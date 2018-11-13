package ru.javaschool.mobileoperator.domain.dto;

import ru.javaschool.mobileoperator.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDto {
    private long id;
    private AddressDto address;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String passport;
    private List<ContractDto> contracts = new ArrayList<>();
    private List<User> users = new ArrayList<>();



    public CustomerDto(long id, AddressDto address, String firstName, String lastName, Date birthDate,
                       String email, String passport) {
        this.id = id;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.passport = passport;
    }

    public CustomerDto(long id, AddressDto address, String firstName, String lastName, Date birthDate, String email,
                       String passport, List<ContractDto> contracts, List<User> users) {
        this.id = id;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.passport = passport;
        this.contracts = contracts;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
