package com.nonso.mybank.exception;

public class MailSendingException extends RuntimeException{
    public MailSendingException(String message) {
        super(message);
    }
}
