package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.ApprovedEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("approved")
@Slf4j
public class ApprovedExecutor implements Executor<ApprovedEventPayload> {

    @Override
    public Class<ApprovedEventPayload> getPayloadType() {
        return ApprovedEventPayload.class;
    }

    @Override
    public void execute(ApprovedEventPayload payload) {
        log.info("approved payload: {}", payload);
    }
}
