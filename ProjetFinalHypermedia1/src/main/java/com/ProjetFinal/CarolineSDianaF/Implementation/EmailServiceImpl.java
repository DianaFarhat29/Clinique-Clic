package com.ProjetFinal.CarolineSDianaF.Implementation;

import com.ProjetFinal.CarolineSDianaF.Interface.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    // Inject repositories for JavaMail
    @Autowired
    private JavaMailSender emailSender;

    // Implementation for sending an email reminder
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@example.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
