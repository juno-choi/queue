package com.juno.queue.event.handler;

import com.juno.queue.event.dto.payload.WithdrawEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("withdraw")
@Slf4j
public class WithdrawEventHandler extends EventHandler<WithdrawEventPayload> {

    @Override
    public Class<WithdrawEventPayload> getPayloadType() {
        return WithdrawEventPayload.class;
    }

    @Override
    protected void handle(WithdrawEventPayload payload) {
        log.info("withdraw payload: {}", payload);
    }
}
