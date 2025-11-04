package com.vijay.service;

import jakarta.mail.MessagingException;

public interface EmailService {

     void sendSimpleEmail(String toEmail, String subject, String body);
     void sendHtmlEmail(String toEmail, String subject,  String htmlContent) throws MessagingException;
}
