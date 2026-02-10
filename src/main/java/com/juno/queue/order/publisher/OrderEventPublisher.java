package com.juno.queue.order.publisher;

import com.juno.queue.order.dto.OrderEvent;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final SnsTemplate snsTemplate;

    @Value("${app.sns.topic-arn}")
    private String topicArn;

    public void publish(OrderEvent event) {
        log.info("Publishing order event: orderId={}, product={}", event.getOrderId(), event.getProduct());
        snsTemplate.convertAndSend(topicArn, event);
    }
}
