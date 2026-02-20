package com.juno.queue.event.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("withdraw")
@Slf4j
public class WithdrawExecutor implements Executor {

    @Override
    public void execute(Object payload) {
        log.info("withdraw payload: {}", payload.toString());
    }
}
