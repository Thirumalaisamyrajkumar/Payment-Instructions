package com.payments.processing.creditcheck.service.impl;

import com.payments.processing.creditcheck.domain.CreditCheckResponse;
import com.payments.processing.creditcheck.entity.Account;
import com.payments.processing.creditcheck.repository.AccountRepository;
import com.payments.processing.creditcheck.service.CreditCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCheckImpl implements CreditCheck {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public String checkCredit(String account, double amount) {
        Account accountEntity = accountRepository.findByAccountId(account);
        if(accountEntity == null){
            return CreditCheckResponse.ACCOUNT_NOT_FOUND.toString();
        }
        else if(!accountEntity.getStatus().equalsIgnoreCase("ACTIVE")){
            return CreditCheckResponse.ACCOUNT_NOT_ACTIVE.toString();
        }
        else if(accountEntity.getBalance() < amount){
            return CreditCheckResponse.INSUFFICIENT_BALANCE.toString();
        }
        return CreditCheckResponse.SUCCESS.toString();
    }
}
