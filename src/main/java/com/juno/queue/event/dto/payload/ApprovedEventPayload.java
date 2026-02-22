package com.juno.queue.event.dto.payload;

import lombok.Data;

@Data
public class ApprovedEventPayload implements EventPayload {
    private String email;
}
