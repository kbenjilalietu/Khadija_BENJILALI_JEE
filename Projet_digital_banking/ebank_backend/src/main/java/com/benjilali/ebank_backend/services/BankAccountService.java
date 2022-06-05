package com.benjilali.ebank_backend.services;



import com.benjilali.ebank_backend.dtos.BankAccountDTO;
import com.benjilali.ebank_backend.dtos.CurrentBankAccountDTO;
import com.benjilali.ebank_backend.dtos.SavingBankAccountDTO;
import com.benjilali.ebank_backend.entities.CurrentAccount;
import com.benjilali.ebank_backend.entities.SavingAccount;
import com.benjilali.ebank_backend.exceptions.BalanceNotSufficientException;
import com.benjilali.ebank_backend.exceptions.BankAccountNotFoundExcetion;
import com.benjilali.ebank_backend.exceptions.CustomerNotFoundException;

import java.util.List;


public interface BankAccountService {


    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, String customerId) throws CustomerNotFoundException;

    BankAccountDTO updateCurrentBankAccount(CurrentAccount currentAccount) throws BankAccountNotFoundExcetion;

    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, String customerId) throws CustomerNotFoundException;


    BankAccountDTO updateSavingBankAccount(SavingAccount savingAccount) throws BankAccountNotFoundExcetion;

    BankAccountDTO getBankAccount(String bankAccountId) throws BankAccountNotFoundExcetion;

    void debit( String accountId, double amount, String description) throws BankAccountNotFoundExcetion, BalanceNotSufficientException;

    void credit( String accountId, double amount, String description) throws BankAccountNotFoundExcetion;

    void transfer( String accountSourceId, String accountDestinationId, double amount, String description) throws BankAccountNotFoundExcetion, BalanceNotSufficientException;

    List<BankAccountDTO> listBankAccountDTO();

    void deleteAccount(String accountId);
}
