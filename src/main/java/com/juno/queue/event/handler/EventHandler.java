package com.juno.queue.event.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.payload.EventPayload;

public abstract class EventHandler<T extends EventPayload> {

    protected abstract Class<T> getPayloadType();

    protected abstract void handle(T payload);

    public void handleRaw(Object rawPayload, ObjectMapper objectMapper) {
        T payload = objectMapper.convertValue(rawPayload, getPayloadType());
        handle(payload);
    }
}
