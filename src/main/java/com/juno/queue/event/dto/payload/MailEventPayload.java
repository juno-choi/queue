package com.juno.queue.event.dto.payload;

import lombok.Data;

@Data
public class MailEventPayload implements EventPayload {
    private String to;
    private String email;
    private String content;
}
