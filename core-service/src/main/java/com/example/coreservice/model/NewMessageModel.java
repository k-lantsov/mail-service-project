package com.example.coreservice.model;

import lombok.Data;

@Data
public class NewMessageModel {

    private String uniqueMessage;

    private Long userGroup;

    private Long templateId;

    private String file;

    private String fileType;

    private Tags tags;

    @Data
    public static class Tags {

        private String day;
    }
}
