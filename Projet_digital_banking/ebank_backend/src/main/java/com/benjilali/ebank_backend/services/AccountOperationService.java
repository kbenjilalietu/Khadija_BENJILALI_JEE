package com.benjilali.ebank_backend.services;

import com.benjilali.ebank_backend.dtos.AccountHistoryDTO;
import com.benjilali.ebank_backend.dtos.AccountOperationDTO;
import com.benjilali.ebank_backend.exceptions.BankAccountNotFoundExcetion;
import com.benjilali.ebank_backend.exceptions.OperationNotFoundException;

import java.util.List;

public interface AccountOperationService {

    public List<AccountOperationDTO> getAccountOperationsHistory(String accountId);

    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundExcetion;

    AccountOperationDTO getOperation(long operationId) throws OperationNotFoundException;
}
