package com.example.coreservice.model.broker;

public record StatusEvent(long messageId, String status, String errorDescription) {
}
