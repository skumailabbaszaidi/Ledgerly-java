package com.kumail.ledgerly.dto.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kumail.ledgerly.entity.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
public class TransactionResponse {
    private Long id;
    private TransactionType type;
    private String description;
    private BigDecimal amount; 
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private LocalDateTime createdAt;

}
