package com.codeup.testrepo.services;
import com.codeup.testrepo.models.Listings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("mailService")
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;//mailSender method

    @Value("${spring.mail.from}")
    private String from;

    public void prepareAndSend(Listings listing, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();//class
        msg.setFrom(from);//setFrom method
        msg.setTo(listing.getUser().getEmail());
        msg.setSubject(subject);
        msg.setText(body);

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
