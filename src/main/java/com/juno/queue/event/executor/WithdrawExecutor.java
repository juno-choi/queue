package com.juno.queue.event.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.payload.WithdrawEventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("withdraw")
@Slf4j
@RequiredArgsConstructor
public class WithdrawExecutor implements Executor {
    private final ObjectMapper objectMapper;

    @Override
    public void execute(Object payload) {
        WithdrawEventPayload withdrawEventPayload = objectMapper.convertValue(payload, WithdrawEventPayload.class);
        log.info("withdraw payload: {}", withdrawEventPayload.toString());
    }
}
