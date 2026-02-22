package com.juno.queue.event.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.payload.MailEventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("mail")
@Slf4j
@RequiredArgsConstructor
public class MailExecutor implements Executor {
    private final ObjectMapper objectMapper;

    @Override
    public void execute(Object payload) {
        MailEventPayload mailEventPayload = objectMapper.convertValue(payload, MailEventPayload.class);
        log.info("mail payload: {}", mailEventPayload.toString());
    }

}
