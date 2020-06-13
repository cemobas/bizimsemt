package com.krakus.bizimsemt.config;

import com.krakus.bizimsemt.aspect.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @Autowired
    private MessageSource validationMsgs;

    @Loggable
    @ExceptionHandler(NoSuchElementException.class)
    public String handleException(NoSuchElementException e, Locale locale, Model model) {
        String errorMessage = validationMsgs.getMessage(
                e.getMessage(), new Object[]{}, locale);
        model.addAttribute("error", errorMessage);
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        return "error";
    }
}
