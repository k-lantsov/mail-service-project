package com.example.coreservice.service.processor;


import com.example.coreservice.model.broker.StatusEvent;

public interface StatusEventProcessor {

    void processEvent(StatusEvent statusEvent);
}
