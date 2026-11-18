package com.kumail.ledgerly.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.kumail.ledgerly.dto.account.AccountResponse;
import com.kumail.ledgerly.dto.account.CreateAccountRequest;
import com.kumail.ledgerly.entity.Account;
import com.kumail.ledgerly.entity.User;
import com.kumail.ledgerly.exception.ResourceNotFoundException;
import com.kumail.ledgerly.repository.AccountRepository;
import com.kumail.ledgerly.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private Long generateAccountNumber () {
        Long accountNumber ;
         do {
                        accountNumber = 1000000000L + (long)(Math.random() * 9000000000L);// Generate a random 10-digit number
         } while (accountRepository.findAccountByAccountNumber(accountNumber).isPresent());
         return accountNumber;
    }
    public AccountResponse createAccount(CreateAccountRequest request, Long userId) {
User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Account account = new Account();
        account.setName(request.getName());
        account.setType(request.getType());
        account.setBalance(request.getInitialBalance());
        account.setAccountNumber(generateAccountNumber());
        account.setUser(user);

        Account savedAccount = accountRepository.save(account);
        return new AccountResponse(savedAccount.getId(), savedAccount.getName(), savedAccount.getType(), savedAccount.getBalance(),savedAccount.getAccountNumber(), savedAccount.getCreatedAt());

        
    } 

    public AccountResponse getAccount(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        
        return new AccountResponse(account.getId(), account.getName(), account.getType(), account.getBalance(), account.getAccountNumber(), account.getCreatedAt());
    }

    public List<AccountResponse> getAccountsByUserId(Long userId){
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        List<Account> accounts = accountRepository.findByUserId(userId);

        return accounts.stream()
            .map( account ->  new AccountResponse (
                account.getId(),
                account.getName(),
                account.getType(),
                account.getBalance(),
                account.getAccountNumber(),
                account.getCreatedAt()
            ))
            .toList();
    }
}
