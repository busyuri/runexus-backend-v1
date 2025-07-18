package domain.ports;

import domain.models.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(Long eventId);

    void joinEvent(Long eventId, Long userId);
    void leaveEvent(Long eventId, Long userId);

    List<Event> getAllEvents();
    Event getEventById(Long eventId);
    List<Event> getAllEventsByUserId(Long userId);



}
