package com.juno.queue.event.listener;

import com.juno.queue.event.dto.MainEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminServiceListener {

    @SqsListener("admin-service")
    public void handle(MainEvent event) {
        log.info("[Admin Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }
}
