package com.juno.queue.event.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("approved")
@Slf4j
public class ApprovedExecutor implements Executor {

    @Override
    public void execute(Object payload) {
        log.info("approved payload: {}", payload.toString());
    }
}
