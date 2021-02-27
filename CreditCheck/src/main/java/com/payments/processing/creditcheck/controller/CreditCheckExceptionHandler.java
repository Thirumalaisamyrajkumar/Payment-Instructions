package com.payments.processing.creditcheck.controller;

import com.payments.processing.creditcheck.domain.CreditCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice(assignableTypes = {CreditCheckController.class})
public class CreditCheckExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CreditCheckExceptionHandler.class);

    @ExceptionHandler(CreditCheckException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleCreditCheckException(CreditCheckException ex){
        logger.error("exception message "+ex.getMessage());
        return ex.getMessage();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        logger.error("Bad request");
        return "BAD_REQUEST";
    }
}
