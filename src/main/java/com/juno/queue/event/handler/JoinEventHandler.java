package com.juno.queue.event.handler;

import com.juno.queue.event.dto.payload.JoinEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("join")
@Slf4j
public class JoinEventHandler extends EventHandler<JoinEventPayload> {

    @Override
    public Class<JoinEventPayload> getPayloadType() {
        return JoinEventPayload.class;
    }

    @Override
    protected void execute(JoinEventPayload payload) {
        log.info("join payload: {}", payload);
    }

}
