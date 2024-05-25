package com.example.coreservice.processor;

import com.example.coreservice.model.NewMessageModel;

public interface NewMessageEventProcessor {

    void processEvent(NewMessageModel newMessageModel);
}
