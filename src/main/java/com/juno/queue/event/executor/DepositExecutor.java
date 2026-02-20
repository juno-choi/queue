package com.juno.queue.event.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("deposit")
@Slf4j
public class DepositExecutor implements Executor {

    @Override
    public void execute(Object payload) {
        log.info("deposit payload: {}", payload.toString());
    }
}
