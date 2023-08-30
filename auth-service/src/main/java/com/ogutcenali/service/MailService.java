package com.ogutcenali.service;

import com.ogutcenali.utils.JwtUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    private final JavaMailSender javaMailSender;
    private final JwtUtil jwtUtil;

    public MailService(JavaMailSender javaMailSender, JwtUtil jwtUtil) {
        this.javaMailSender = javaMailSender;
        this.jwtUtil = jwtUtil;
    }


    public void sendEmail(String toEmail,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("aliogutcen@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);

    }

    public void activationCodeSendEmail(String mail, String code) {
        String token = jwtUtil.activationToken(mail, code);
        String subject = "Activation Code";
        String body = "Activation CODE: "+" "+code+" or use link " + "http://localhost:8080/activation-code/"+token
        ;
        sendEmail(mail, subject, body);
    }
}
