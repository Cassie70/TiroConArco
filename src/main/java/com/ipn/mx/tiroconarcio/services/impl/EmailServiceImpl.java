package com.ipn.mx.tiroconarcio.services.impl;

import com.ipn.mx.tiroconarcio.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarEmail(String destinatario, String asunto, String mensaje) {
        try{
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(destinatario);
            email.setSubject(asunto);
            email.setText(mensaje);
            mailSender.send(email);

            logger.info("Email enviado a: {}", destinatario);
        }catch (MailException e ){
            logger.error("Error al enviar el email a {}: {}", destinatario, e.getMessage());
        }catch (Exception e) {
            logger.error("Error inesperado al enviar el email a {}: {}", destinatario, e.getMessage());
        }

    }

    @Override
    public void enviarNotificacion(String texto) {

    }
}
