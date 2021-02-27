package com.payments.processing.creditcheck.repository;

import com.payments.processing.creditcheck.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface AccountRepository extends CrudRepository<Account,String>{
    public Account findByAccountId(String accountId);
}
