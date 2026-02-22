package com.juno.queue.event.handler;

import com.juno.queue.event.dto.payload.DepositEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("deposit")
@Slf4j
public class DepositEventHandler extends EventHandler<DepositEventPayload> {

    @Override
    public Class<DepositEventPayload> getPayloadType() {
        return DepositEventPayload.class;
    }

    @Override
    protected void execute(DepositEventPayload payload) {
        log.info("deposit payload: {}", payload);
    }

}
