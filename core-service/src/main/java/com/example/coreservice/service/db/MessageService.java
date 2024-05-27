package com.example.coreservice.service.db;

import com.example.coreservice.entity.Message;
import com.example.shared.model.NewMessageEvent;
import com.example.shared.model.StatusEvent;

public interface MessageService {

    Message saveNewMessage(NewMessageEvent newMessageEvent);

    void updateMessageStatus(StatusEvent statusEvent);
}
