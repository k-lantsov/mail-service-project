package com.example.shared.model;

import lombok.Data;

@Data
public class NewMessageEvent {

    private String uniqueMessage;

    private Long userGroup;

    private Long templateId;

    private String file;

    private String fileType;

    private Tags data;

    @Data
    public static class Tags {

        private String day;
    }
}
