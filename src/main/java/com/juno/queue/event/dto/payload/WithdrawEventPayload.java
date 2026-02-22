package com.juno.queue.event.dto.payload;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawEventPayload implements EventPayload {
    private BigDecimal amount;
}
