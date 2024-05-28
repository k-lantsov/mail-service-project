package com.example.coreservice.service.db;

import com.example.coreservice.entity.Message;
import com.example.coreservice.model.broker.NewMessageEvent;
import com.example.coreservice.model.broker.StatusEvent;

public interface MessageService {

    Message saveNewMessage(NewMessageEvent newMessageEvent);

    void updateMessageStatus(StatusEvent statusEvent);
}
