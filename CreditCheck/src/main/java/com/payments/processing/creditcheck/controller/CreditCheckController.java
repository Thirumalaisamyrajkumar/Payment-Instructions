package com.payments.processing.creditcheck.controller;

import com.payments.processing.creditcheck.domain.CreditCheckRequest;
import com.payments.processing.creditcheck.service.CreditCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCheckController {
    private static final Logger logger = LoggerFactory.getLogger(CreditCheckController.class);

    @Autowired
    private CreditCheck creditCheck;

    @PostMapping(value = "/check", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public String creditCheck(@RequestBody CreditCheckRequest request ){
        logger.debug("credit check - start");

        return creditCheck.checkCredit(request.getDebtorAccount(),request.getPaymentAmount());

    }
}
