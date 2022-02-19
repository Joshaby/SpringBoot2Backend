package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

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
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
        messageHelper.setTo(pedido.getCliente().getEmail());
        messageHelper.setFrom(sender);
        messageHelper.setSubject(String.format("Pedido confirmado! Código: %d", pedido.getId()));
        messageHelper.setSentDate(new Date(System.currentTimeMillis()));
        messageHelper.setText(pedidoToHtml(pedido), true);
        return mailMessage;
    }

    protected String pedidoToHtml(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process("email/confirmacaoPedido", context);
    }
}
