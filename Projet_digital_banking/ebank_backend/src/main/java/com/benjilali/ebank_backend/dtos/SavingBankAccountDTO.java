package com.benjilali.ebank_backend.dtos;


import com.benjilali.ebank_backend.enums.AccountStatus;
import lombok.Data;
import java.util.Date;


@Data
public class SavingBankAccountDTO extends BankAccountDTO {

    private String id;

    private double balance;

    private Date createdAt;

    private CustomerDTO customer;

    private AccountStatus status;

    private double interestRate;

}
