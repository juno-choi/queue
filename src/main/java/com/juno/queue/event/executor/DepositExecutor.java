package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.DepositEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("deposit")
@Slf4j
public class DepositExecutor implements Executor<DepositEventPayload> {

    @Override
    public void execute(DepositEventPayload payload) {
        log.info("deposit payload: {}", payload.toString());
    }

    @Override
    public Class<DepositEventPayload> getPayloadType() {
        return DepositEventPayload.class;
    }
}
