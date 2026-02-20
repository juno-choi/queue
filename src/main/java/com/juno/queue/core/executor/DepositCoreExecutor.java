package com.juno.queue.core.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("deposit")
@Slf4j
public class DepositCoreExecutor implements CoreExecutor{

    @Override
    public void execute(String payload) {
        log.info("deposit payload: {}", payload);
    }
}
