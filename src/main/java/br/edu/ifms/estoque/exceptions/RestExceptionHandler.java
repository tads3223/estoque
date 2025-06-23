/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author 1513003
 */
@RestControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageApiError> argumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        MessageApiError apiError = new MessageApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                errorList);
        return new ResponseEntity<>(apiError, 
                HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageApiError> notFoundException(
            DataIntegrityViolationException ex
    ) {
        MessageApiError apiError = new MessageApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(
                apiError, 
                HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({
        SubgrupoNotExistException.class,
        MarcaNotFoundException.class,
        UnidadeMedidaNotFoundException.class
    })
    public ResponseEntity<MessageApiError> notFoundException(
            RuntimeException ex
    ) {
        MessageApiError apiError = new MessageApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(
                apiError, 
                HttpStatus.NOT_FOUND);
    }
}
