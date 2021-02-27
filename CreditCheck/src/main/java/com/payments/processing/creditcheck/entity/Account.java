package com.payments.processing.creditcheck.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    private String accountId;
    private double balance;
    private String status;
}
