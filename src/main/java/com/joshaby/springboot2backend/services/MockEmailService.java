package com.joshaby.springboot2backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOG.info("Enviando email...");
        LOG.info(mailMessage.toString());
        LOG.info("Email enviado");
    }

    @Override
    public void sendHTMLEmail(MimeMessage mailMessage) {
        LOG.info("Enviando email com html...");
        LOG.info(mailMessage.toString());
        LOG.info("Email enviado!");
    }
}
