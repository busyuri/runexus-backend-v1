package com.example.runexus.application.controllers;

import com.example.runexus.application.dto.EventInput;
import com.example.runexus.domain.models.Event;
import com.example.runexus.domain.ports.EventService;
import com.example.runexus.infrastructure.mapper.EventMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventInput eventInput) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventService.createEvent(eventMapper.inputToDomain(eventInput)));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody EventInput eventInput) {
        Event updatedEvent = eventService.updateEvent(eventId, eventMapper.inputToDomain(eventInput));
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId, @RequestParam Long userId) {
        boolean deleted = eventService.deleteEvent(eventId, userId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{eventId}/join")
    public ResponseEntity<Void> joinEvent(@PathVariable Long eventId, @RequestParam Long userId) {
        eventService.joinEvent(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{eventId}/leave")
    public ResponseEntity<Void> leaveEvent(@PathVariable Long eventId, @RequestParam Long userId) {
        eventService.leaveEvent(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/myevents")
    public ResponseEntity<List<Event>> getMyEvents(@RequestParam Long userId) {
        List<Event> joinedEvents = eventService.findEventsByUserId(userId);
        return ResponseEntity.ok(joinedEvents);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Event>> getEventsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(eventService.getEventByUserId(userId));
    }
}

