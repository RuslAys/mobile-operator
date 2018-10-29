package ru.javaschool.mobileoperator.service;

import org.springframework.ui.Model;
import ru.javaschool.mobileoperator.domain.PhoneNumber;

import java.util.List;

public interface PhoneNumberService extends GenericService<PhoneNumber, Long> {
    String getAllNumbers(Model model);
    List<PhoneNumber> getAllNumbers();
    List<PhoneNumber> getAllEmptyNumbers();
    String addNumber(String number);
}
