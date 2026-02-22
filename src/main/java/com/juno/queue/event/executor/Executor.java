package com.juno.queue.event.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.payload.EventPayload;

public abstract class Executor<T extends EventPayload> {

    protected abstract Class<T> getPayloadType();

    protected abstract void execute(T payload);

    public void executeRaw(Object rawPayload, ObjectMapper objectMapper) {
        T payload = objectMapper.convertValue(rawPayload, getPayloadType());
        execute(payload);
    }
}
