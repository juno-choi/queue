package com.juno.queue.aml.listener;

import com.juno.queue.aml.executor.AmlExecutor;
import com.juno.queue.event.dto.MainEvent;
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
public class AmlServiceListener {
    private final Map<String, AmlExecutor> executorMap;

    @SqsListener(value = "aml-service", acknowledgementMode = SqsListenerAcknowledgementMode.MANUAL)
    public void handle(MainEvent event, Acknowledgement acknowledgement) {
        String eventTypeName = event.getEventType().name();
        AmlExecutor executor = executorMap.get(eventTypeName);

        if (executor == null) {
            log.warn("not found executor: {}", eventTypeName);
            return;
        }

        executor.execute(event.getPayload());
        acknowledgement.acknowledge();
        log.info("[Aml Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }
}
