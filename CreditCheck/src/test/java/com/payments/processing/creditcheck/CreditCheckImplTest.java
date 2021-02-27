package com.payments.processing.creditcheck;

import com.payments.processing.creditcheck.domain.CreditCheckResponse;
import com.payments.processing.creditcheck.entity.Account;
import com.payments.processing.creditcheck.repository.AccountRepository;
import com.payments.processing.creditcheck.service.impl.CreditCheckImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.PropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@PropertySource("classpath:application.properties")
@ExtendWith(MockitoExtension.class)
public class CreditCheckImplTest {

    @InjectMocks
    private CreditCheckImpl creditCheck;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccessMessage(){
        when(accountRepository.findByAccountId(any())).thenReturn(getAccount(123.12,"ACTIVE"));

        String response = creditCheck.checkCredit("123445", 12.12);
        assertEquals(CreditCheckResponse.SUCCESS.toString(),response );
    }

    @Test
    public void testNotFoundMessage(){
        when(accountRepository.findByAccountId(any())).thenReturn(null);

        String response = creditCheck.checkCredit("12344", 12.12);
        assertEquals(CreditCheckResponse.ACCOUNT_NOT_FOUND.toString(),response );
    }

    @Test
    public void testNotActiveMessage(){
        when(accountRepository.findByAccountId(any())).thenReturn(getAccount(123.12,"INACTIVE"));

        String response = creditCheck.checkCredit("123445", 12.12);
        assertEquals(CreditCheckResponse.ACCOUNT_NOT_ACTIVE.toString(),response );
    }

    @Test
    public void testInsufficientFund(){
        when(accountRepository.findByAccountId(any())).thenReturn(getAccount(123.12,"ACTIVE"));

        String response = creditCheck.checkCredit("123445", 1442.12);
        assertEquals(CreditCheckResponse.INSUFFICIENT_BALANCE.toString(),response );
    }
    private Account getAccount(double amount, String status) {
        Account account = new Account();
        account.setAccountId("123445");
        account.setBalance(amount);
        account.setStatus(status);

        return account;
    }
}
