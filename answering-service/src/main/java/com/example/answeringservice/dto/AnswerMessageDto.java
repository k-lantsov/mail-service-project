package com.example.answeringservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerMessageDto {

    @JsonProperty(value = "message")
    private String incomingMessageRequest;

    @JsonProperty(value = "errors")
    private String errorDescription;

    @JsonProperty(value = "date")
    private LocalDateTime created;

    @JsonProperty(value = "status")
    private String messageStatus;

    @JsonProperty(value = "date_status")
    private LocalDateTime updated;
}
