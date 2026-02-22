package com.juno.queue.notification.listener;

import com.juno.queue.event.dto.DefaultEvent;
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
    private final Map<String, Executor> executorMap;

    @SqsListener(value = "notification-service", acknowledgementMode = SqsListenerAcknowledgementMode.MANUAL)
    public void handle(DefaultEvent event, Acknowledgement acknowledgement) {
        // eventType에 따라 실행할 executor 가져오기
        String eventTypeName = event.getEventType().name();
        Executor executor = executorMap.get(eventTypeName);

        if (executor == null) {
            log.warn("not found executor: {}", eventTypeName);
            return;
        }

        // event payload를 executor에서 사용하는 payload로 변환
        executor.execute(event.getPayload());

        // 모두 성공했다면 ack
        acknowledgement.acknowledge();
        log.info("[Notification Service] Received event: eventId={}, eventType={}, payload={}",
                event.getEventId(), event.getEventType(), event.getPayload());
    }
}
