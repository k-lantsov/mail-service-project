package com.example.mailservice.service;

import com.example.shared.model.MailEvent;

public interface MailService {

    void sendSimpleMail(MailEvent mailEvent);

    void sendMailWithAttachment(MailEvent mailEvent);
}
