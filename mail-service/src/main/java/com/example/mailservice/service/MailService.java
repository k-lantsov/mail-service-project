package com.example.mailservice.service;


import com.example.mailservice.model.broker.MailEvent;

public interface MailService {

    void sendSimpleMail(MailEvent mailEvent);

    void sendMailWithAttachment(MailEvent mailEvent);
}
