package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.entities.Pedido;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@NoArgsConstructor
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    protected TemplateEngine templateEngine;

    protected JavaMailSender javaMailSender;

    public AbstractEmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage mailMessage = prepareMailMessage(pedido);
        sendEmail(mailMessage);
    }

    @Override
    public void sendOrderConfirmationHTMLEmail(Pedido pedido) {
        try {
            MimeMessage maiMessage = prepareHtmlMailMessage(pedido);
            sendHTMLEmail(maiMessage);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
    }

    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
        SimpleMailMessage mailMessage = prepareNewPasswordEmail(cliente, newPassword);
        sendEmail(mailMessage);
    }

    protected SimpleMailMessage prepareMailMessage(Pedido pedido) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(pedido.getCliente().getEmail());
        mailMessage.setFrom(sender);
        mailMessage.setSubject(String.format("Pedido confimado! Código: %d", pedido.getId()));
        mailMessage.setSentDate(new Date(System.currentTimeMillis()));
        mailMessage.setText(pedido.toString());
        return mailMessage;
    }

    protected MimeMessage prepareHtmlMailMessage(Pedido pedido) throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
        messageHelper.setTo(pedido.getCliente().getEmail());
        messageHelper.setFrom(sender);
        messageHelper.setSubject(String.format("Pedido confirmado! Código: %d", pedido.getId()));
        messageHelper.setSentDate(new Date(System.currentTimeMillis()));
        messageHelper.setText(pedidoToHtml(pedido), true);
        return mailMessage;
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPassword) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(cliente.getEmail());
        mailMessage.setFrom(sender);
        mailMessage.setSubject("Solicitação de nova senha");
        mailMessage.setSentDate(new Date(System.currentTimeMillis()));
        mailMessage.setText(String.format("Nova senha: %s", newPassword));
        return mailMessage;
    }

    protected String pedidoToHtml(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process("email/confirmacaoPedido", context);
    }
}
