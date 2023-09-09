package com.grupo18.nocountry.greenpoint;

import com.grupo18.nocountry.greenpoint.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,ErrorResponse>> validException(MethodArgumentNotValidException ex) {
        Map<String,ErrorResponse> response = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> response
                        .put(error.getField(),ErrorResponse.builder()
                                .message(error.getDefaultMessage())
                                .status(HttpStatus.BAD_REQUEST.toString())
                                .timestamp(LocalDateTime.now())
                                .build()))
                ;
        logger.error(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({IdNotFoundException.class, UsernameNotFoundException.class, RecyclableNotFound.class})
    public ResponseEntity<ErrorResponse> notFound(RuntimeException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.toString())
                .timestamp(LocalDateTime.now())
                .build();

        logger.error(response);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNotFoundException.class,OutOfStockException.class,UserAlreadyExists.class, InvalidRecycleCode.class, ConfirmationTokenException.class,RecyclableAlreadyExist.class, RewardNotFoundException.class, InsufficientPointsException.class})
    public ResponseEntity<ErrorResponse> badRequest(RuntimeException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();

        logger.error(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerError(Exception ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error " + ex.getMessage());
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConversion(RuntimeException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erros " +ex.getMessage());
    }

}