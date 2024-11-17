package com.travelbuddy.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.travelbuddy.common.exception.auth.InvalidLoginCredentialsException;
import com.travelbuddy.common.exception.errorresponse.*;
import com.travelbuddy.common.exception.userinput.InvaidOtpException;
import com.travelbuddy.common.exception.userinput.UserInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.naming.SizeLimitExceededException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                .withMessage(ex.getMessage())
                .build());
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDataAlreadyExistsException(DataAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.builder()
                .withMessage(ex.getMessage())
                .build());
    }

    @ExceptionHandler(InvaidOtpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvaidOtpException(InvaidOtpException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .withMessage(ex.getMessage())
                .build());
    }

    @ExceptionHandler(InvalidLoginCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidLoginCredentialsException(InvalidLoginCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .withMessage(ex.getMessage() != null ? ex.getMessage() : "Email or password is incorrect")
                .build());
    }

    @ExceptionHandler(InvaidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvaidTokenException(InvaidTokenException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .withMessage(ex.getMessage())
                .build());
    }

    @ExceptionHandler(UserInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleUserInputException(UserInputException ex) {
        ErrorResponse.Builder errorBuilder = ErrorResponse.builder();
        if(ex.getMessage() != null) {
            errorBuilder.withMessage(ex.getMessage());
        } else {
            errorBuilder.withMessage("Invalid input");
        }

        List<String> errorFields = ex.getErrorFields();
        if (errorFields != null && !errorFields.isEmpty()) {
            ObjectNode error = objectMapper.createObjectNode();
            for (String field : errorFields) {
                String firstError = ex.getFirstErrorMessage(field);
                error.put(field, firstError != null ? firstError : "");
            }
            errorBuilder.withError(error);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBuilder.build());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse.builder()
                .withMessage(ex.getMessage())
                .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Void> handleAllUncaughtException(Exception ex) {
        log.error("Uncaught exception [ERROR: {}]", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        ErrorResponse.Builder errorBuilder = ErrorResponse.builder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            ObjectNode errorNode = objectMapper.createObjectNode();
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorNode.put(fieldName, errorMessage);
            errorBuilder.withError(errorNode);
        });
        errorBuilder.withMessage("Invalid input");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBuilder.build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: " + ex.getMessage());
    }

    @ExceptionHandler(BadTimingInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadTimingInputException(BadTimingInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .withMessage(ex.getMessage())
                .build());
    }

    @ExceptionHandler(EnumNotFitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleEnumNotFitException(EnumNotFitException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .withMessage(ex.getMessage())
                .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .withMessage(ex.getMessage())
                .build());
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class, SizeLimitExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMaxUploadSizeExceededException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .withMessage("File size is too large " + ex.getMessage())
                .build());
    }
}
