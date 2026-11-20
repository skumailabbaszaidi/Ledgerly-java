package com.kumail.ledgerly.dto.transaction;

import java.math.BigDecimal;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class TransactionRequest {
    @NotNull 
    private BigDecimal amount;

    private String description;
    
    
}
