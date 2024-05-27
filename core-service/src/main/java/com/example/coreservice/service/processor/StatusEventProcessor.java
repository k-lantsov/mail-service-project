package com.example.coreservice.service.processor;

import com.example.shared.model.StatusEvent;

public interface StatusEventProcessor {

    void processEvent(StatusEvent statusEvent);
}
