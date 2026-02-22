package com.juno.queue.event.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.payload.DepositEventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("deposit")
@Slf4j
@RequiredArgsConstructor
public class DepositExecutor implements Executor {
    private final ObjectMapper objectMapper;

    @Override
    public void execute(Object payload) {
        DepositEventPayload depositEventPayload = objectMapper.convertValue(payload, DepositEventPayload.class);
        log.info("deposit payload: {}", depositEventPayload.toString());
    }

}
