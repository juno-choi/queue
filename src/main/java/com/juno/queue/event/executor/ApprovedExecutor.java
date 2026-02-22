package com.juno.queue.event.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.payload.ApprovedEventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("approved")
@Slf4j
@RequiredArgsConstructor
public class ApprovedExecutor implements Executor {
    private final ObjectMapper objectMapper;

    @Override
    public void execute(Object payload) {
        ApprovedEventPayload approvedEventPayload = objectMapper.convertValue(payload, ApprovedEventPayload.class);
        log.info("approved payload: {}", approvedEventPayload.toString());
    }
}
