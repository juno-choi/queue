package com.juno.queue.controller;

import com.juno.queue.event.dto.DefaultEvent;
import com.juno.queue.event.dto.PublishEventRequest;
import com.juno.queue.event.publisher.MainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final MainEventPublisher mainEventPublisher;

    @PostMapping
    public ResponseEntity<Map<String, String>> publishEvent(@RequestBody PublishEventRequest request) {
        DefaultEvent event = DefaultEvent.builder()
                .eventId(request.getEventId())
                .eventType(request.getEventType())
                .payload(request.getPayload())
                .build();

        mainEventPublisher.publish(event);

        return ResponseEntity.ok(Map.of(
                "eventId", request.getEventId(),
                "eventType", request.getEventType().name(),
                "status", "published"
        ));
    }
}
