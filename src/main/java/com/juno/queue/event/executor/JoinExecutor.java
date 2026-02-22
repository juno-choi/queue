package com.juno.queue.event.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.payload.JoinEventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("join")
@Slf4j
@RequiredArgsConstructor
public class JoinExecutor implements Executor {
    private final ObjectMapper objectMapper;

    @Override
    public void execute(Object payload) {
        JoinEventPayload joinEventPayload = objectMapper.convertValue(payload, JoinEventPayload.class);
        log.info("join payload: {}", joinEventPayload.toString());
    }

}
