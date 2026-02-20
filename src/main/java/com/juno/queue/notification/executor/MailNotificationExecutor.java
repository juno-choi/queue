package com.juno.queue.notification.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("mail")
@Slf4j
public class MailNotificationExecutor implements NotificationExecutor{
    @Override
    public void execute(String payload) {
        log.info("mail payload: {}", payload);
    }
}
