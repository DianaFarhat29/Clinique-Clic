package com.ProjetFinal.CarolineSDianaF.Interface;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    // Method for sending an email reminder
    public void sendSimpleMessage(String to, String subject, String text);

}
