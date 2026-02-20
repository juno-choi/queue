package com.juno.queue.event.dto;

public enum EventType {
    // core, notification
    deposit,
    withdraw,
    // aml, notification
    join,
    approved,
    // mail
    mail,
    ;
}
