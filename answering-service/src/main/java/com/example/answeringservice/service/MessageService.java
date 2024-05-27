package com.example.answeringservice.service;

import com.example.answeringservice.dto.AnswerMessageDto;

public interface MessageService {

    AnswerMessageDto getInfo(String uniqueMessage);
}
