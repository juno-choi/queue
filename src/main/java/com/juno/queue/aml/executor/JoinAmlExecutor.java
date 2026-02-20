package com.juno.queue.aml.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("join")
@Slf4j
public class JoinAmlExecutor implements AmlExecutor{

    @Override
    public void execute(String payload) {
        log.info("join payload: {}", payload);
    }
}
