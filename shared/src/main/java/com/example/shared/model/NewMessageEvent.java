package com.example.shared.model;

public class NewMessageEvent {

    private  String uniqueMessage;

    private long userGroup;

    private long templateId;

    private String file;

    private String fileType;

    private Tags tags;

    public NewMessageEvent() {
    }

    public NewMessageEvent(String uniqueMessage, Long userGroup, Long templateId, String file, String fileType, Tags tags) {
        this.uniqueMessage = uniqueMessage;
        this.userGroup = userGroup;
        this.templateId = templateId;
        this.file = file;
        this.fileType = fileType;
        this.tags = tags;
    }

    public String getUniqueMessage() {
        return uniqueMessage;
    }

    public void setUniqueMessage(String uniqueMessage) {
        this.uniqueMessage = uniqueMessage;
    }

    public Long getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Long userGroup) {
        this.userGroup = userGroup;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }
}
