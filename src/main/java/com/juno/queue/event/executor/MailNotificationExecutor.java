package com.juno.queue.event.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("mail")
@Slf4j
public class MailNotificationExecutor implements Executor {
    @Override
    public void execute(String payload) {
        log.info("mail payload: {}", payload);
    }
}
