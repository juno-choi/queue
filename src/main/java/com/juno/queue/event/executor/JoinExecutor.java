package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.JoinEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("join")
@Slf4j
public class JoinExecutor extends Executor<JoinEventPayload> {

    @Override
    public Class<JoinEventPayload> getPayloadType() {
        return JoinEventPayload.class;
    }

    @Override
    protected void execute(JoinEventPayload payload) {
        log.info("join payload: {}", payload);
    }

    @Override
    public void executeSaga(JoinEventPayload payload) {
        log.info("saga execute: {}", payload);
    }
}
