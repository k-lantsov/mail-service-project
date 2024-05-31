package com.example.mailservice.model.broker;

public record StatusEvent(long messageId, String status, String errorDescription) {
}
