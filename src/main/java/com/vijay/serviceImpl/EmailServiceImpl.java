package com.vijay.serviceImpl;

import com.vijay.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    Logger log = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     *  this method to use send mail
     * @param toEmail user email
     * @param subject email subject
     * @param body  email body
     */
    @Override
    public void sendSimpleEmail(String toEmail, String subject, String body) {
        log.info("Insider sendSimpleEmail method with parameter {}{}{}",toEmail,subject,body);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        log.info("Mail Sent Successfully...");
    }

    /**
     * this method to use send mail as Html page
     * @param toEmail  user email
     * @param subject  email subject
     * @param htmlContent html content
     * @throws MessagingException exception
     */
    @Override
    public void sendHtmlEmail(String toEmail, String subject,  String htmlContent) throws MessagingException {
        log.info("Inside sendHtmlEmail with param {}{}{}",toEmail,subject,htmlContent);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true = isHtml
        javaMailSender.send(mimeMessage);
        log.info("Mail Sent Successfully.....");
    }
}
