package com.juno.queue.notification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juno.queue.event.dto.DefaultEvent;
import com.juno.queue.event.dto.payload.EventPayload;
import com.juno.queue.event.executor.Executor;
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
    private final Map<String, Executor<?>> executorMap;
    private final ObjectMapper objectMapper;

    @SqsListener(value = "notification-service", acknowledgementMode = SqsListenerAcknowledgementMode.MANUAL)
    public void handle(DefaultEvent event, Acknowledgement acknowledgement) {
        String eventTypeName = event.getEventType().name();
        Executor<?> executor = executorMap.get(eventTypeName);

        if (executor == null) {
            log.warn("not found executor: {}", eventTypeName);
            return;
        }

        resolveAndExecute(executor, event.getPayload());
        acknowledgement.acknowledge();
        log.info("[Notification Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }

    private <T extends EventPayload> void resolveAndExecute(Executor<T> executor, Object rawPayload) {
        T payload = objectMapper.convertValue(rawPayload, executor.getPayloadType());
        executor.execute(payload);
    }
}
