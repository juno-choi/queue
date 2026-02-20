package com.juno.queue.event.dto.payload;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class DepositEventPayload implements EventPayload {
    private BigDecimal amount;
}
