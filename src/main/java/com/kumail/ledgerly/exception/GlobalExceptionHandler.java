package com.kumail.ledgerly.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kumail.ledgerly.dto.exception.ExceptionResponse;

@ControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler 
    public ResponseEntity<ExceptionResponse> handleUsernameExists (UsernameAlreadyExistsException e){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDateTime.now());
      return ResponseEntity.status(HttpStatus.CONFLICT).body(response) ;   } 
        

    @ExceptionHandler 
    public ResponseEntity<ExceptionResponse> handleEmailExists (EmailAlreadyExistsException e){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(ResourceNotFoundException e){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    } 

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleInvalidCredentials(InvalidCredentialsException e){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), LocalDateTime.now());
return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
  @ExceptionHandler 
    public ResponseEntity<ExceptionResponse> handleValidation(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler 
    public ResponseEntity<ExceptionResponse> handleGeneral(Exception e){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something Went Wrong", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

  
}
