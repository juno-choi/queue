package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.JoinEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("join")
@Slf4j
public class JoinExecutor implements Executor<JoinEventPayload> {

    @Override
    public void execute(JoinEventPayload payload) {
        log.info("join payload: {}", payload.toString());
    }

    @Override
    public Class<JoinEventPayload> getPayloadType() {
        return JoinEventPayload.class;
    }
}
