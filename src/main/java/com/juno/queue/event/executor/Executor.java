package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.EventPayload;

public interface Executor<T extends EventPayload> {
    Class<T> getPayloadType();

    void execute(T payload);
}
