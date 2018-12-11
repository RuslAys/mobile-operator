package ru.javaschool.mobileoperator.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.javaschool.mobileoperator.service.exceptions.ContractException;
import ru.javaschool.mobileoperator.service.exceptions.OptionException;
import ru.javaschool.mobileoperator.service.exceptions.PhoneNumberException;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String handleInternalServerError(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return "errors/internal_error";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class})
    public String handleBadRequest(Model model, HttpServletRequest request, Exception e){
        e.printStackTrace();
        model.addAttribute("info", e.getMessage());
        return "errors/bad_request";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ContractException.class, OptionException.class,
            PhoneNumberException.class, TariffPlanException.class, })
    public String handleBadRequestCustomErrors(Model model, HttpServletRequest request, Exception e){
        e.printStackTrace();
        model.addAttribute("info", e.getMessage());
//        logger.error(e.getMessage());
        return "errors/bad_request";
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundException(Model model, HttpServletRequest request, Exception e){
        e.printStackTrace();
//        logger.error(e.getMessage());
        return "errors/not_found";
    }
}
