package ru.javaschool.mobileoperator.controller.advice;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.javaschool.mobileoperator.service.exceptions.ContractException;
import ru.javaschool.mobileoperator.service.exceptions.OptionException;
import ru.javaschool.mobileoperator.service.exceptions.PhoneNumberException;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String handleInternalServerError(Exception e) {
        log.error(e);
        return "errors/internal_error";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class})
    public String handleBadRequest(Model model, Exception e) {
        log.error(e);
        return "errors/bad_request";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ContractException.class, OptionException.class,
            PhoneNumberException.class, TariffPlanException.class})
    public String handleBadRequestCustomErrors(Model model, Exception e) {
        log.error(e);
        return "errors/bad_request";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundException(Model model, Exception e) {
        log.error(e);
        return "errors/not_found";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public String handleValidationException(Exception e){
        log.error(e);
        return "errors/bad_request";
    }
}
