package com.juno.queue.event.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("mail")
@Slf4j
public class MailExecutor implements Executor {

    @Override
    public void execute(Object payload) {
        log.info("mail payload: {}", payload.toString());
    }
}
