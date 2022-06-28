package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.entities.Pedido;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage mailMessage);

    void sendOrderConfirmationHTMLEmail(Pedido pedido);

    void sendHTMLEmail(MimeMessage mailMessage);

    void sendNewPasswordEmail(Cliente cliente, String newPassword);
}
