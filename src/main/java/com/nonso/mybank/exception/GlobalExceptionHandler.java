package com.nonso.mybank.exception;

import com.nonso.mybank.dto.request.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ApiResponse<String> handleUserNotFoundException(UserNotFoundException e) {
        logger.error(e.getMessage());
        return new ApiResponse<>("failed",e.getMessage(),null);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleNotFoundException(ResourceNotFoundException ex){
        logger.error(ex.getMessage());
        return  new ApiResponse<>("Failed","Error: " + ex.getMessage(), null);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<String> handleValidationException(ValidationException ex){
        logger.error(ex.getMessage());
        return  new ApiResponse<>("Failed","Error: " + ex.getMessage(), null);
    }

    @ExceptionHandler(MailSendingException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiResponse<String> handleMailSendingException(MailSendingException ex){
        logger.error(ex.getMessage());
        return  new ApiResponse<>("Failed","Error: " + ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<String> handleInvalidTransactionException(InvalidTransactionException ex){
        logger.error(ex.getMessage());
        return  new ApiResponse<>("Failed","Error: " + ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiResponse<String> handleInvalidCredentialsException(InvalidCredentialsException ex){
        logger.error(ex.getMessage());
        return  new ApiResponse<>("Failed","Error: " + ex.getMessage(), null);
    }
}
