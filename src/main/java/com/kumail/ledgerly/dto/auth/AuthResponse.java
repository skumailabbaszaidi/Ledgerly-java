package com.kumail.ledgerly.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
public class AuthResponse {
    private Long id;
    private String name;
    private String email;
    private String username;
    
}
