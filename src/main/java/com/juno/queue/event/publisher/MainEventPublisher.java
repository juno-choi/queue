package com.juno.queue.event.publisher;

import com.juno.queue.event.dto.DefaultEvent;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainEventPublisher {

    private final SnsTemplate snsTemplate;

    @Value("${app.sns.topic-arn}")
    private String topicArn;

    public void publish(DefaultEvent event) {
        log.info("Publishing event: eventId={}, eventType={}, payload={}", event.getEventId(), event.getEventType(), event.getPayload().toString());
        snsTemplate.convertAndSend(topicArn, event);
    }
}
