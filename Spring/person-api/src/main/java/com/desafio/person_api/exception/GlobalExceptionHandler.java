package com.desafio.person_api.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Violação de Regra de Negócio", ex.getMessage(), request.getRequestURI(), null);
    }

    // coloquei para identificar erro de parametro
    @ExceptionHandler(org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ApiError> handleInvalidDataAccess(org.springframework.dao.InvalidDataAccessApiUsageException ex, HttpServletRequest request) {
        return buildErrorResponse(
            HttpStatus.BAD_REQUEST, 
            "Parâmetro de ordenação inválido", 
            "O campo informado para ordenação não existe.", 
            request.getRequestURI(), 
            null
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return buildErrorResponse(
            HttpStatus.BAD_REQUEST, 
            "Erro de Validação", 
            "Um ou mais campos estão incorretos", 
            request.getRequestURI(), 
            errors
        );
    }

    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String error, String message, String path, Map<String, String> fieldErrors) {
        ApiError apiError = new ApiError(
            LocalDateTime.now(),
            status.value(),
            error,
            message,
            path,
            fieldErrors
        );
        return ResponseEntity.status(status).body(apiError);
    }
}
