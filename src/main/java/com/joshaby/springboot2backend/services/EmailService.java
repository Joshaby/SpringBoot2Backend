package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.entities.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage mailMessage);

    void sendOrderConfirmationHTMLEmail(Pedido pedido);

    void sendHTMLEmail(MimeMessage mailMessage);
//
//    void sendNewPasswordEmail(Cliente cliente, String newPassword);
}
