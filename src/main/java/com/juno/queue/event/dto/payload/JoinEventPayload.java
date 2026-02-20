package com.juno.queue.event.dto.payload;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JoinEventPayload implements EventPayload {
    private String email;
}
