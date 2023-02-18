package com.nonso.mybank.configuration.mail;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RequiredArgsConstructor
public class GmailService implements EmailService{

    private static final Logger LOGGER = LoggerFactory.getLogger(GmailService.class);

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("${spring.mail.username}");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);

        LOGGER.info(String.format("Email Sent to -> %s", to));

    }
}
