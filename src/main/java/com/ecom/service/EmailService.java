package com.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender gmailMailSender;

    @Autowired
    private JavaMailSender hotmailMailSender;

    /**
     * Envío de email de verificación basado en el dominio del correo.
     */
    public void sendVerificationEmail(String to, String subject, String text) {
        String domain = getDomainFromEmail(to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        if (domain.equals("gmail.com")) {
            gmailMailSender.send(message);
        } else if (domain.equals("hotmail.com") || domain.equals("outlook.com") || domain.equals("live.com")) {
            hotmailMailSender.send(message);
        } else {
            throw new IllegalArgumentException("Proveedor de email no soportado para el dominio: " + domain);
        }
    }

    /**
     * Extraer el dominio del email.
     */
    private String getDomainFromEmail(String email) {
        return email.substring(email.lastIndexOf("@") + 1);
    }
}


