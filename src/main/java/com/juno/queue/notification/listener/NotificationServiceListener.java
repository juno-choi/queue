package com.juno.queue.notification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.DefaultEvent;
import com.juno.queue.event.handler.EventHandler;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.annotation.SqsListenerAcknowledgementMode;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationServiceListener {
    private final Map<String, EventHandler<?>> handlerMap;
    private final ObjectMapper objectMapper;

    @SqsListener(value = "notification-service", acknowledgementMode = SqsListenerAcknowledgementMode.MANUAL)
    public void consume(DefaultEvent event, Acknowledgement acknowledgement) {
        String eventTypeName = event.getEventType().name();
        EventHandler<?> handler = handlerMap.get(eventTypeName);

        // inbox 구현 가능

        if (handler == null) {
            log.warn("not found handler: {}", eventTypeName);
            return;
        }

        try {
            handler.handleRaw(event.getPayload(), objectMapper);
        } catch (Exception e) {
            log.warn("handler fail: {}", e.getMessage());
        }

        acknowledgement.acknowledge();
        log.info("[Notification Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }
}
