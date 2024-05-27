package com.example.shared.model;


import java.util.List;

public record MailEvent(long messageId, List<String> emails, String messageText, byte[] file) {

}
