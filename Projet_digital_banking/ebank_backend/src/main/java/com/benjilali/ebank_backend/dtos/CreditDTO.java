package com.benjilali.ebank_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDTO {

    private String accountId;
    private double amount;
    private String description;
    

}
