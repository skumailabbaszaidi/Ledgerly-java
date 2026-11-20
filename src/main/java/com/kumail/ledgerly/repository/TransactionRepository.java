package com.kumail.ledgerly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumail.ledgerly.entity.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

    List<Transaction> findByAccountId(Long accountId);
}
