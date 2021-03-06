package com.benjilali.ebank_backend.web;

import com.benjilali.ebank_backend.dtos.AccountHistoryDTO;
import com.benjilali.ebank_backend.dtos.AccountOperationDTO;
import com.benjilali.ebank_backend.dtos.BankAccountDTO;
import com.benjilali.ebank_backend.dtos.BankAccountRequestDTO;
import com.benjilali.ebank_backend.exceptions.BankAccountNotFoundExcetion;
import com.benjilali.ebank_backend.exceptions.CustomerNotFoundException;
import com.benjilali.ebank_backend.mappers.BankAccountMapper;
import com.benjilali.ebank_backend.services.AccountOperationService;
import com.benjilali.ebank_backend.services.BankAccountService;
import com.benjilali.ebank_backend.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
@CrossOrigin("*")
public class BankAccountRestController {

    private BankAccountService bankAccountService;
    private AccountOperationService operationService;
    private CustomerService customerService;
    private BankAccountMapper bankAccountMapper;

    @GetMapping("/accounts/{accountId}")
    @PreAuthorize("hasAuthority('USER')")
    public BankAccountDTO getBankAccount(@PathVariable("accountId") String accountId) throws BankAccountNotFoundExcetion {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    @PreAuthorize("hasAuthority('USER')")
    public List<BankAccountDTO> accountsList() {
        return bankAccountService.listBankAccountDTO();
    }

    @GetMapping("/accounts/{accountId}/operations")
    @PreAuthorize("hasAuthority('USER')")
    public List<AccountOperationDTO> getHistory(@PathVariable("accountId") String accountId) {
        return operationService.getAccountOperationsHistory(accountId);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/accounts/{accountId}/paginateOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable("accountId") String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) throws BankAccountNotFoundExcetion {
        return operationService.getAccountHistory(accountId, page, size);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/accounts")
    public BankAccountDTO saveBankAccount(@RequestBody BankAccountRequestDTO accountRequestDTO) throws CustomerNotFoundException {
        BankAccountDTO dto;
        if (accountRequestDTO.getType().equals("SavingAccount")) {
            this.customerService.getCustomer(accountRequestDTO.getCustomer().getId());
            dto = bankAccountService.saveSavingBankAccount(
                    accountRequestDTO.getBalance(),
                    accountRequestDTO.getInterestRate(),
                    accountRequestDTO.getCustomer().getId()
            );
        } else {
            this.customerService.getCustomer(accountRequestDTO.getCustomer().getId());
            dto = bankAccountService.saveCurrentBankAccount(
                    accountRequestDTO.getBalance(),
                    accountRequestDTO.getOverDraft(),
                    accountRequestDTO.getCustomer().getId()
            );
        }
        return dto;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/accounts/{id}")
    public BankAccountDTO updateBankAccount(@PathVariable(name = "id") String accountId, @RequestBody BankAccountRequestDTO accountRequestDTO) throws BankAccountNotFoundExcetion {
        accountRequestDTO.setId(accountId);
        if (accountRequestDTO.getType().equals("SavingAccount"))
            return bankAccountService.updateSavingBankAccount(
                    bankAccountMapper.savingAccountFromBankAccountRequestDto(accountRequestDTO)
            );

        return bankAccountService.updateCurrentBankAccount(
                bankAccountMapper.currentAccountFromBankAccountRequestDto(accountRequestDTO)
        );
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/accounts/{id}")
    public void deleteBankAccount(@PathVariable(name = "id") String accountId ) throws BankAccountNotFoundExcetion {
         bankAccountService.deleteAccount( accountId );
    }
}
