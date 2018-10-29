package ru.javaschool.mobileoperator.repository;

import ru.javaschool.mobileoperator.domain.PhoneNumber;

import java.util.List;

public interface PhoneNumberDao extends GenericDao<PhoneNumber, Long> {
    List<PhoneNumber> getAllEmptyNumbers();
}
