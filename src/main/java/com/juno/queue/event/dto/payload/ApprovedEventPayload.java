package com.juno.queue.event.dto.payload;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApprovedEventPayload implements EventPayload {
    private String email;
}
