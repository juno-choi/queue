package com.juno.queue.core.listener;

import com.juno.queue.event.dto.MainEvent;
import com.juno.queue.core.executor.CoreExecutor;
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
public class CoreServiceListener {
    private final Map<String, CoreExecutor> executorMap;

    @SqsListener(value = "core-service", acknowledgementMode = SqsListenerAcknowledgementMode.MANUAL)
    public void handle(MainEvent event, Acknowledgement acknowledgement) {
        String eventTypeName = event.getEventType().name();
        CoreExecutor coreExecutor = executorMap.get(eventTypeName);

        if (coreExecutor == null) {
            log.warn("not found executor: {}", eventTypeName);
            return;
        }

        coreExecutor.execute();
        acknowledgement.acknowledge();
        log.info("[Core Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }
}
