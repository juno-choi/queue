package com.juno.queue.notification.listener;

import com.juno.queue.aml.executor.AmlExecutor;
import com.juno.queue.event.dto.MainEvent;
import com.juno.queue.notification.executor.NotificationExecutor;
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
    private final Map<String, NotificationExecutor> executorMap;

    @SqsListener(value = "notification-service", acknowledgementMode = SqsListenerAcknowledgementMode.MANUAL)
    public void handle(MainEvent event, Acknowledgement acknowledgement) {
        String eventTypeName = event.getEventType().name();
        NotificationExecutor executor = executorMap.get(eventTypeName);

        if (executor == null) {
            log.warn("not found executor: {}", eventTypeName);
            return;
        }

        executor.execute(event.getPayload());
        acknowledgement.acknowledge();
        log.info("[Notification Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }
}
