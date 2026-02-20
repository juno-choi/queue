package com.juno.queue.event.executor;

import com.juno.queue.event.dto.payload.WithdrawEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("withdraw")
@Slf4j
public class WithdrawExecutor implements Executor<WithdrawEventPayload> {

    @Override
    public void execute(WithdrawEventPayload payload) {
        log.info("withdraw payload: {}", payload.toString());
    }

    @Override
    public Class<WithdrawEventPayload> getPayloadType() {
        return WithdrawEventPayload.class;
    }
}
