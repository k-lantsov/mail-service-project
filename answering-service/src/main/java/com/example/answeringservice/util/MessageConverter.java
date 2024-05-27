package com.example.answeringservice.util;

import com.example.answeringservice.dto.NewMessageDto;
import com.example.shared.model.NewMessageEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageConverter {

    private final ModelMapper modelMapper;

    public NewMessageEvent convertNewMessageDtoToNewMessageEvent(NewMessageDto newMessageDto) {
        return modelMapper.map(newMessageDto, NewMessageEvent.class);
    }
}
