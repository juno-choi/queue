package com.juno.queue.order.listener;

import com.juno.queue.order.dto.OrderEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderNotificationListener {

    @SqsListener("order-notification-queue")
    public void handle(OrderEvent event) {
        log.info("[Notification] Sending notification for order: orderId={}, customer={}",
                event.getOrderId(), event.getCustomerName());
    }
}
