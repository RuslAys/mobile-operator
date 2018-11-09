package ru.javaschool.mobileoperator.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TariffPlanException extends RuntimeException {
    public TariffPlanException() {
    }

    public TariffPlanException(String message) {
        super(message);
    }
}
