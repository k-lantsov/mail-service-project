package com.example.answeringservice.service;

import com.example.answeringservice.model.controller.AnswerMessageDto;

public interface MessageService {

    AnswerMessageDto getInfo(String uniqueMessage);
}
