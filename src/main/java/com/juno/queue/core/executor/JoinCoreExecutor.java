package com.juno.queue.core.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("join")
@Slf4j
public class JoinCoreExecutor implements CoreExecutor {

    @Override
    public void execute() {
        log.info("join core execute...");
    }
}
