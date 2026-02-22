package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.ApprovedEventPayload;
import com.juno.queue.event.dto.payload.DepositEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("deposit")
@Slf4j
public class DepositExecutor extends Executor<DepositEventPayload> {

    @Override
    public Class<DepositEventPayload> getPayloadType() {
        return DepositEventPayload.class;
    }

    @Override
    protected void execute(DepositEventPayload payload) {
        log.info("deposit payload: {}", payload);
    }

    @Override
    public void executeSaga(DepositEventPayload payload) {
        log.info("saga execute: {}", payload);
    }
}
