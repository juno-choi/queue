package com.juno.queue.event.handler;

import com.juno.queue.event.dto.payload.ApprovedEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("approved")
@Slf4j
public class ApprovedEventHandler extends EventHandler<ApprovedEventPayload> {

    @Override
    public Class<ApprovedEventPayload> getPayloadType() {
        return ApprovedEventPayload.class;
    }

    @Override
    protected void execute(ApprovedEventPayload payload) {
        log.info("approved payload: {}", payload);
    }
}
