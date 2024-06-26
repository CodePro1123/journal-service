package com.journal.service.journel.payload.User;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEvent {
    private EventType type;
    private User user;
    private LocalDateTime timestamp;

    public enum EventType {
        CREATED,
        UPDATED,
        DELETED,
        RETRIEVED
    }

    public UserEvent(EventType type, User user, LocalDateTime timestamp) {
        this.type = type;
        this.user = user;
        this.timestamp = timestamp;
    }

}
