package com.kumail.ledgerly.dto.account;


import java.math.BigDecimal;

import com.kumail.ledgerly.entity.AccountType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data 

public class CreateAccountRequest {
   
    @NotBlank
    private String name;

    @NotNull
    private AccountType type;

    @NotNull 
    private BigDecimal initialBalance;
    

}
