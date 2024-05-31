package com.example.answeringservice.service;

import com.example.answeringservice.entity.Message;
import com.example.answeringservice.exception.NoDataException;
import com.example.answeringservice.model.controller.AnswerMessageDto;
import com.example.answeringservice.repository.MessageRepository;
import com.example.answeringservice.util.MessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;
    private final MessageConverter messageConverter;

    @Override
    public AnswerMessageDto getInfo(String uniqueMessage) {
        Message message = messageRepository.findByUniqueMessage(uniqueMessage)
                .orElseThrow(() -> new NoDataException("Message is not found by unique_message=" + uniqueMessage));
        return messageConverter.convertMessageToAnswerMessageDto(message);
    }
}
