package com.kumail.ledgerly.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kumail.ledgerly.dto.transaction.TransactionRequest;
import com.kumail.ledgerly.dto.transaction.TransactionResponse;
import com.kumail.ledgerly.entity.Account;
import com.kumail.ledgerly.entity.Transaction;
import com.kumail.ledgerly.entity.TransactionType;
import com.kumail.ledgerly.exception.InsufficientBalanceException;
import com.kumail.ledgerly.exception.ResourceNotFoundException;
import com.kumail.ledgerly.repository.AccountRepository;
import com.kumail.ledgerly.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service 
@RequiredArgsConstructor 
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional 
    public TransactionResponse createTransaction (TransactionRequest request, Long accountId, TransactionType type){
        Account account = accountRepository.findById(accountId).orElseThrow(() ->  new ResourceNotFoundException   ("Account not found"));
        Transaction transaction = new Transaction();
        transaction.setType(type); 
        transaction.setAmount(request.getAmount()); 
        transaction.setBalanceBefore(account.getBalance());
        transaction.setDescription(request.getDescription());
        transaction.setAccount(account);
        if (type == TransactionType.DEPOSIT){
            BigDecimal amount = account.getBalance().add(request.getAmount());
            account.setBalance(amount);

            accountRepository.save(account);
        }
        else {
            if(account.getBalance().compareTo(request.getAmount()) < 0){
                throw new InsufficientBalanceException("Insufficient Balance");
            }
            else{
                BigDecimal amount = account.getBalance().subtract(request.getAmount());
                account.setBalance(amount);
                accountRepository.save(account);
            }

        }
        transaction.setBalanceAfter(account.getBalance());
        Transaction saved = transactionRepository.save(transaction);

        return new TransactionResponse(saved.getId(), saved.getType(), saved.getDescription(), saved.getAmount(), saved.getBalanceBefore(), saved.getBalanceAfter(), saved.getCreatedAt());
       
    }
    public  List<TransactionResponse> getTransactionByAccount(Long accountId){
        accountRepository.findById(accountId).orElseThrow(()-> new ResourceNotFoundException("Account not found"));
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions.stream()
            .map( transaction -> new TransactionResponse(
                transaction.getId(),
                transaction.getType(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getBalanceBefore(),
                transaction.getBalanceAfter(),
                transaction.getCreatedAt()
            )).toList();
    }
    
    
}
