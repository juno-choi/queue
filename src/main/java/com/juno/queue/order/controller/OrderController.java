package com.juno.queue.order.controller;

import com.juno.queue.order.dto.OrderEvent;
import com.juno.queue.order.dto.PublishOrderRequest;
import com.juno.queue.order.publisher.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderEventPublisher orderEventPublisher;

    @PostMapping
    public ResponseEntity<Map<String, String>> publishOrder(@RequestBody PublishOrderRequest request) {
        String orderId = UUID.randomUUID().toString();

        OrderEvent event = OrderEvent.builder()
                .orderId(orderId)
                .customerName(request.getCustomerName())
                .product(request.getProduct())
                .quantity(request.getQuantity())
                .totalPrice(request.getTotalPrice())
                .build();

        orderEventPublisher.publish(event);

        return ResponseEntity.ok(Map.of("orderId", orderId, "status", "published"));
    }
}
