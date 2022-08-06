package com.joshaby.springboot2backend.config;

import com.joshaby.springboot2backend.services.EmailService;
import com.joshaby.springboot2backend.services.SMTPEmailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

@Configuration
@AllArgsConstructor
public class MailConfig {

    private TemplateEngine templateEngine;

    private JavaMailSender javaMailSender;

    private MailSender mailSender;

    @Bean
    public EmailService getEmailService() {
        return new SMTPEmailService(templateEngine, javaMailSender, mailSender);
    }
}
