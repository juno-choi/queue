package com.juno.queue.aml.listener;

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
public class AmlServiceListener {
    private final Map<String, Executor<?>> executorMap;
    private final ObjectMapper objectMapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SqsListener(value = "aml-service", acknowledgementMode = SqsListenerAcknowledgementMode.MANUAL)
    public void handle(DefaultEvent event, Acknowledgement acknowledgement) {
        String eventTypeName = event.getEventType().name();
        Executor executor = executorMap.get(eventTypeName);

        if (executor == null) {
            log.warn("not found executor: {}", eventTypeName);
            return;
        }

        EventPayload converted = (EventPayload) objectMapper.convertValue(event.getPayload(), executor.getPayloadType());
        executor.execute(converted);
        acknowledgement.acknowledge();
        log.info("[Aml Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }
}
