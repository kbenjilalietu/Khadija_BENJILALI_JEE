package com.benjilali.ebank_backend.web;

import com.benjilali.ebank_backend.dtos.AccountOperationDTO;
import com.benjilali.ebank_backend.dtos.CreditDTO;
import com.benjilali.ebank_backend.dtos.DebitDTO;
import com.benjilali.ebank_backend.dtos.TransferRequestDTO;
import com.benjilali.ebank_backend.exceptions.BalanceNotSufficientException;
import com.benjilali.ebank_backend.exceptions.BankAccountNotFoundExcetion;
import com.benjilali.ebank_backend.exceptions.OperationNotFoundException;
import com.benjilali.ebank_backend.services.AccountOperationService;
import com.benjilali.ebank_backend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
@CrossOrigin("*")
public class OperationRestController {


    private AccountOperationService operationService;
    private BankAccountService accountService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/operations/{id}")
    public AccountOperationDTO getOperation(@PathVariable("id") long operationId) throws OperationNotFoundException {
        return operationService.getOperation( operationId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/operations/debit")
    public DebitDTO debiter(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundExcetion, BalanceNotSufficientException {

        accountService.debit( debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription() );
        return debitDTO;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/operations/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundExcetion {

        accountService.credit( creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription() );
        return creditDTO;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/operations/transfer")
    public TransferRequestDTO transferer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundExcetion, BalanceNotSufficientException {

        accountService.transfer(
                transferRequestDTO.getAccountSourceId(),
                transferRequestDTO.getAccountDestinationId(),
                transferRequestDTO.getAmount(),
                transferRequestDTO.getDescription()
        );
        return transferRequestDTO;

    }


}
