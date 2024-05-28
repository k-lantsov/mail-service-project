package com.example.coreservice.model.broker;


import java.util.List;

public record MailEvent(long messageId, List<String> emails, String messageText, byte[] file, String fileType) {

}
