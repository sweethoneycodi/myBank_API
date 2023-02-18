package com.nonso.mybank.configuration.mail;

public interface EmailService {
    void sendMail(String to, String subject, String message);
}
