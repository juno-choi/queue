package com.juno.queue.event.listener;

import com.juno.queue.event.dto.MainEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceListener {

    @SqsListener("user-service")
    public void handle(MainEvent event) {
        log.info("[User Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }
}
