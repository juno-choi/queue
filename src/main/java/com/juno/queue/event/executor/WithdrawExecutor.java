package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.WithdrawEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("withdraw")
@Slf4j
public class WithdrawExecutor extends Executor<WithdrawEventPayload> {

    @Override
    public Class<WithdrawEventPayload> getPayloadType() {
        return WithdrawEventPayload.class;
    }

    @Override
    protected void execute(WithdrawEventPayload payload) {
        log.info("withdraw payload: {}", payload);
    }

    @Override
    public void executeSaga(WithdrawEventPayload payload) {
        log.info("saga execute: {}", payload);
    }
}
