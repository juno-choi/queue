package com.juno.queue.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PublishOrderRequest {

    private String customerName;
    private String product;
    private int quantity;
    private long totalPrice;
}
