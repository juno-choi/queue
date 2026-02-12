package com.juno.queue.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainEvent {

    private String eventId;
    private EventType eventType;
    private String payload;
}
