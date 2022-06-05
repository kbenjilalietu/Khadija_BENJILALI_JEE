package com.benjilali.ebank_backend;

import com.benjilali.ebank_backend.dtos.BankAccountDTO;
import com.benjilali.ebank_backend.dtos.CurrentBankAccountDTO;
import com.benjilali.ebank_backend.dtos.CustomerDTO;
import com.benjilali.ebank_backend.dtos.SavingBankAccountDTO;
import com.benjilali.ebank_backend.exceptions.CustomerNotFoundException;
import com.benjilali.ebank_backend.security.entities.AppRole;
import com.benjilali.ebank_backend.security.entities.AppUser;
import com.benjilali.ebank_backend.security.service.SecurityService;
import com.benjilali.ebank_backend.services.BankAccountService;
import com.benjilali.ebank_backend.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBackendApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner initSecurityDatas(SecurityService securityService){
        return args -> {
            securityService.addNewRole(new AppRole(null,"USER"));
            securityService.addNewRole(new AppRole(null,"ADMIN"));
            securityService.addNewUser( new AppUser( null, "user", "user", new ArrayList<>()));
            securityService.addNewUser( new AppUser( null, "admin", "admin", new ArrayList<>()));

            securityService.addRoleToUser( "user", "USER");
            securityService.addRoleToUser( "admin", "USER");
            securityService.addRoleToUser( "admin", "ADMIN");

        };
    }
    //@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService, CustomerService customerService){
        return args -> {
            Stream.of("Ahlam","Asmaa","Akram").forEach(name->{
                CustomerDTO customer=new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name.replaceAll(" ", "")+"@gmail.com");
                customerService.saveCustomer(customer);
            });
            customerService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts = bankAccountService.listBankAccountDTO();
            for (BankAccountDTO bankAccount:bankAccounts){
                for (int i = 0; i <10 ; i++) {
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    } else{
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }
            }
        };
    }

}
