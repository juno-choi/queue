package com.juno.queue.order.listener;

import com.juno.queue.order.dto.OrderEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderProcessingListener {

    @SqsListener("order-processing-queue")
    public void handle(OrderEvent event) {
        log.info("[Processing] Order received: orderId={}, product={}, quantity={}",
                event.getOrderId(), event.getProduct(), event.getQuantity());
    }
}
