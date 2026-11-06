package com.kumail.ledgerly.dto.auth;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data 
public class RegisterRequest {

    @NotBlank
    private String name;

    @NotBlank 
    private String username;
    
    @NotBlank
    @Size(min=6 )
    private String password;

    @NotBlank 
    @Email 
    private String email;
}

