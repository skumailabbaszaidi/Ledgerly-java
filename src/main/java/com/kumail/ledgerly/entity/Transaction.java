package com.kumail.ledgerly.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data 
@Entity 
@Table (name = "transactions")
public class Transaction {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @Column (nullable = false)
    private BigDecimal amount;

    @Column (nullable = false)
    private BigDecimal balanceBefore;

    @Column (nullable = false)
    private BigDecimal balanceAfter;

    @Enumerated (EnumType.STRING)
    @Column (nullable  = false)
    private TransactionType type;
    
    @Column
    private String description; 

    @ManyToOne 
    @JoinColumn (name="account_id", nullable = false )
    private Account account;
    @Column (nullable = false)
    private LocalDateTime createdAt;

    @Column (nullable = false)
    private LocalDateTime updatedAt;
                      
    @PrePersist 
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
