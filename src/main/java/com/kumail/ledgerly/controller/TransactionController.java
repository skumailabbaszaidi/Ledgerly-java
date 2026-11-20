package com.kumail.ledgerly.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumail.ledgerly.dto.transaction.TransactionRequest;
import com.kumail.ledgerly.dto.transaction.TransactionResponse;
import com.kumail.ledgerly.entity.TransactionType;
import com.kumail.ledgerly.service.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/accounts")
@RequiredArgsConstructor 
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping ("/{accountId}/withdraw")
    public ResponseEntity<TransactionResponse> createTransactionWithdraw (@RequestBody @Valid TransactionRequest request, @PathVariable Long accountId ){
        TransactionResponse response = transactionService.createTransaction(request, accountId, TransactionType.WITHDRAW);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    } 

    @PostMapping ("/{accountId}/deposit")
    public ResponseEntity<TransactionResponse> createTransactionDeposit (@RequestBody @Valid TransactionRequest request, @PathVariable Long accountId ){
        TransactionResponse response = transactionService.createTransaction(request, accountId, TransactionType.DEPOSIT);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping ("/{accountId}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions (@PathVariable Long accountId){
        List<TransactionResponse> response = transactionService.getTransactionByAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
