package ru.javaschool.mobileoperator.service;

import org.springframework.ui.Model;
import ru.javaschool.mobileoperator.domain.PhoneNumber;

import java.util.List;

public interface PhoneNumberService extends GenericService<PhoneNumber, Long> {
    List<PhoneNumber> getAllNumbers();
    List<PhoneNumber> getAllEmptyNumbers();
    void addNumber(String number);
}
