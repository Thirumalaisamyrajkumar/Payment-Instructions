package com.payments.processing.creditcheck.domain;

public enum CreditCheckResponse {

    SUCCESS("SUCCESS"),
    ACCOUNT_NOT_ACTIVE("ACCOUNT_NOT_ACTIVE"),
    INSUFFICIENT_BALANCE("INSUFFICIENT_BALANCE"),
    ACCOUNT_NOT_FOUND("ACCOUNT_NOT_FOUND");

    private String value;
    CreditCheckResponse(String value){
        this.value=value;
    }


}
