package com.heycristhian.springemail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")
    private String providerEmail;

    public void sendEmail(String email) {
        try {
            log.info("Starting the email sending process");
            var message = new SimpleMailMessage();
            message.setFrom(providerEmail);
            message.setTo(email);
            message.setSubject("New user registered successfully!");
            message.setText("Congratulations! Your registration has been successfully!");
            emailSender.send(message);
            log.info("Successful email sending");
        } catch (Exception e) {
            log.error("Error seding email");
            e.printStackTrace();
        }
    }
}
