package com.juno.queue.event.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("join")
@Slf4j
public class JoinExecutor implements Executor {

    @Override
    public void execute(Object payload) {
        log.info("join payload: {}", payload.toString());
    }
}
