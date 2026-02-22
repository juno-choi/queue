package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.MailEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("mail")
@Slf4j
public class MailExecutor extends Executor<MailEventPayload> {

    @Override
    public Class<MailEventPayload> getPayloadType() {
        return MailEventPayload.class;
    }

    @Override
    protected void execute(MailEventPayload payload) {
        log.info("mail payload: {}", payload);
    }
}
