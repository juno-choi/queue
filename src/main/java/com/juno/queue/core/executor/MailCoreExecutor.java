package com.juno.queue.core.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("mail")
@Slf4j
public class MailCoreExecutor implements CoreExecutor {

    @Override
    public void execute() {
        log.info("mail core execute...");
    }
}
