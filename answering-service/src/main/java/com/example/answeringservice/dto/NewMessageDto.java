package com.example.answeringservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewMessageDto {

    @JsonProperty(value = "unique_message")
    @NotNull
    private String uniqueMessage;

    @JsonProperty(value = "group_users")
    @NotNull
    private Long userGroup;

    @JsonProperty(value = "template_id")
    @NotNull
    private Long templateId;

    @JsonProperty(value = "file")
    private String file;

    @JsonProperty(value = "type_file")
    private String fileType;

    @JsonProperty(value = "data")
    private Tags tags;

    @Data
    public static class Tags {

        @JsonProperty(value = "$day")
        private String day;

    }
}
