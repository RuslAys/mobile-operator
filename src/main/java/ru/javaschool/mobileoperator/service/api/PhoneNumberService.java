package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.PhoneNumber;

import java.util.List;

public interface PhoneNumberService extends GenericService<PhoneNumber, Long> {
    /**
     * Method to find all phone numbers
     * @return List with phone numbers
     */
    List<PhoneNumber> getAllNumbers();

    /**
     * Method to find all phone number without relations to terminal devices
     * @return list with phone numbers
     */
    List<PhoneNumber> getAllEmptyNumbers();

    /**
     * Method to add new phone number to data base
     * @param number phone number
     */
    void addNumber(String number);
}
