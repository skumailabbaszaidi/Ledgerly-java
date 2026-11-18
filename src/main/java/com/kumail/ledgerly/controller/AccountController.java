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

import com.kumail.ledgerly.dto.account.AccountResponse;
import com.kumail.ledgerly.dto.account.CreateAccountRequest;
import com.kumail.ledgerly.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/accounts")
@RequiredArgsConstructor 
public class AccountController {
    private final AccountService accountService;

    @PostMapping ("/{userId}")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request, @PathVariable Long userId){
        AccountResponse response = accountService.createAccount(request, userId);
         
        return ResponseEntity.status(HttpStatus.CREATED).body(response); 
    }

    @GetMapping ("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id){
        AccountResponse response = accountService.getAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    

    @GetMapping ("/user/{userId}")
    public ResponseEntity<List<AccountResponse>> getAccountByUserId(@PathVariable Long userId){
        List<AccountResponse> response  = accountService.getAccountsByUserId(userId);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
        
    
}
