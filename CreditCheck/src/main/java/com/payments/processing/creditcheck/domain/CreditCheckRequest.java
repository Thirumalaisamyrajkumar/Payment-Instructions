package com.payments.processing.creditcheck.domain;

import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreditCheckRequest {

    private String messageId;
    private String transactionId;
    private String endToEndId;
    @NotBlank
    private String debtorAccount ;
    private String paymentCurrency ;
    @NotBlank
    private double paymentAmount ;
    private String valueDate;
}
