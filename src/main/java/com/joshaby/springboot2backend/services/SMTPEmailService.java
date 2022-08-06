package com.joshaby.springboot2backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;

public class SMTPEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

    private final MailSender mailSender;

    public SMTPEmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender, MailSender mailSender) {
        super(templateEngine, javaMailSender);
        this.mailSender = mailSender;
    }

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
