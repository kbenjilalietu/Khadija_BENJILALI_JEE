package com.benjilali.ebank_backend.services;

import com.benjilali.ebank_backend.dtos.CustomerAccountsDTO;
import com.benjilali.ebank_backend.dtos.CustomerDTO;
import com.benjilali.ebank_backend.dtos.CustomerPageableDTO;
import com.benjilali.ebank_backend.entities.Customer;
import com.benjilali.ebank_backend.exceptions.CustomerNotFoundException;
import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomer(String customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException;

    void deleteCustomer(String customerId);

    List<CustomerDTO> searchCustomer( String searchKeyword);


    CustomerPageableDTO searchCustomerPaginated(int page, int size, String searchKeyword);

    CustomerAccountsDTO getCustomerAccounts(String customerId, int page, int size) throws CustomerNotFoundException;

    CustomerPageableDTO paginateCustomers(int page, int size);
}
