package application.controllers;

import domain.models.Event;
import domain.ports.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping
    public ResponseEntity<Event> update(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(event));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> delete(@PathVariable Long eventId) {
        boolean deleted = eventService.deleteEvent(eventId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{eventId}/join")
    public ResponseEntity<Void> join(@PathVariable Long eventId, @RequestParam Long userId) {
        eventService.joinEvent(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{eventId}/leave")
    public ResponseEntity<Void> leave(@PathVariable Long eventId, @RequestParam Long userId) {
        eventService.leaveEvent(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getById(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Event>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(eventService.getAllEventsByUserId(userId));
    }
}
