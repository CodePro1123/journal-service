package com.journal.service.journel.config;

import com.journal.service.journel.payload.User.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalServiceConfig {

    private final List<String> journal = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(JournalServiceConfig.class);

    @KafkaListener(topics = "user-events", groupId = "journal-group")
    public void consumeUserEvent(UserEvent event) {
        // Log the user event
        logger.info("User Event: {} - {}", event.getType(), event.getUser().toString());
        // Record the event in the journal (database)
        recordEvent(event);
    }

    private void recordEvent(UserEvent event) {
        // For simplicity, just adding the event details to the list
        journal.add(event.toString());
    }

    // RESTful API endpoint to retrieve journals
    @GetMapping
    public List<String> getJournal() {
        return journal;
    }
}
