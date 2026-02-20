package com.juno.queue.event.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("approved")
@Slf4j
public class ApprovedAmlExecutor implements Executor {
    @Override
    public void execute(String payload) {
        log.info("approved payload: {}", payload);
    }
}
