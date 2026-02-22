package com.juno.queue.event.dto.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class DepositEventPayload implements EventPayload {
    private BigDecimal amount;
}
