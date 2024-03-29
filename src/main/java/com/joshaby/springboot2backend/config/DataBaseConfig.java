package com.joshaby.springboot2backend.config;

import com.joshaby.springboot2backend.services.DataBaseService;
import com.joshaby.springboot2backend.services.EmailService;
import com.joshaby.springboot2backend.services.MockEmailService;
import com.joshaby.springboot2backend.services.SMTPEmailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.text.ParseException;

@Configuration
@Profile("test")
@AllArgsConstructor
public class DataBaseConfig {

    private DataBaseService dataBaseService;

    @Bean
    public boolean initializaH2() throws ParseException {
        dataBaseService.initializeDataBase();
        return true;
    }
}
