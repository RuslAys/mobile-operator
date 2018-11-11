package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.PhoneNumber;

import java.util.List;

public interface PhoneNumberDao extends GenericDao<PhoneNumber, Long> {
    /**
     * Method to find all phone numbers without relations with terminal devices
     * @return list with phone numbers
     */
    List<PhoneNumber> getAllEmptyNumbers();
}
