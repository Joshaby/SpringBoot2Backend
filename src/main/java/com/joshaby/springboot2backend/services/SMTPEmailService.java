package com.joshaby.springboot2backend.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SMTPEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOG.info("Enviando email...");
        mailSender.send(mailMessage);
        LOG.info("Email enviado");
    }

    @Override
    public void sendHTMLEmail(MimeMessage mailMessage) {
        LOG.info("Enviando email...");
        javaMailSender.send(mailMessage);
        LOG.info("Email enviado");
    }
}
