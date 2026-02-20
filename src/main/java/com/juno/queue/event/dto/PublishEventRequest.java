package com.juno.queue.event.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PublishEventRequest {
    private String eventId;
    private EventType eventType;
    private Object payload;
}
