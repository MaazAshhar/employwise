package com.assignment.employwise.services.impl;

import com.assignment.employwise.services.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public void sendEmailToManager(String employeeName, String email, String employeeMail,String phoneNumber) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            helper.setFrom(sender);
            helper.setTo(email);
            helper.setSubject("New Employee Joined");
            String mailContent = employeeName+" will now work under you. Mobile number is <a href=\"tel:"+phoneNumber+"\">"+phoneNumber+"</a> and email is <a href=\"mailto:"+employeeMail+"\">"+employeeMail+"</a>";
            helper.setText(mailContent,true);
            javaMailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
