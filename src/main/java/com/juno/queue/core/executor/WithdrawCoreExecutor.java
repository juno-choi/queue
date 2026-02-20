package com.juno.queue.core.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("withdraw")
@Slf4j
public class WithdrawCoreExecutor implements CoreExecutor{
    @Override
    public void execute(String payload) {
        log.info("withdraw payload: {}", payload);
    }
}
