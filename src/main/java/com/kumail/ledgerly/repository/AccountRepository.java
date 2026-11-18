package com.kumail.ledgerly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumail.ledgerly.entity.Account;
import java.util.*;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByAccountNumber(Long accountNumber); 
    
    List<Account> findByUserId(Long userId);
}
