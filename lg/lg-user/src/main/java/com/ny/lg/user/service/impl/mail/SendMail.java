package com.ny.lg.user.service.impl.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/8/28 21:33
 * @description:
 **/
@Service
public class SendMail {
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendMailToHello(String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("770266906@163.com");
        msg.setBcc();
        msg.setTo("770266906gg@163.com");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        msg.setSubject(localDateTime.toLocalDate() + " 预警");
        msg.setText(message);
        try {
            javaMailSender.send(msg);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

}
