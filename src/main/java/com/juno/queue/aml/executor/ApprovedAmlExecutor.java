package com.juno.queue.aml.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("approved")
@Slf4j
public class ApprovedAmlExecutor implements AmlExecutor{
    @Override
    public void execute(String payload) {
        log.info("approved payload: {}", payload);
    }
}
