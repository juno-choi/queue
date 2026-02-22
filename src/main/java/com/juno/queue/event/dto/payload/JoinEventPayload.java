package com.juno.queue.event.dto.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class JoinEventPayload implements EventPayload {
    private String email;
}
