package com.kumail.ledgerly.dto.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kumail.ledgerly.entity.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
public class AccountResponse {
    private Long id;
    private String name;
    private AccountType type;
    private BigDecimal balance;
    private Long accountNumber;
    private LocalDateTime createdAt;
    
}
