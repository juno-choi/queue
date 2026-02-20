package com.juno.queue.event.executor;

public interface Executor<T> {
    void execute(T payload);
    Class<T> getPayloadType();
}
