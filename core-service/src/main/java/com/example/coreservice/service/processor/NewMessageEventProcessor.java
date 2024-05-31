package com.example.coreservice.service.processor;


import com.example.coreservice.model.broker.NewMessageEvent;

public interface NewMessageEventProcessor {

    void processEvent(NewMessageEvent newMessageEvent);
}
