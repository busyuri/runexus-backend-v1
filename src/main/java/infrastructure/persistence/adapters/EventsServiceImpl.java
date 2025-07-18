package infrastructure.persistence.adapters;

import domain.models.Event;
import domain.models.User;
import domain.ports.EventService;
import infrastructure.persistence.repositories.EventRepository;
import infrastructure.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Event createEvent(Event event){
        event.setParticipantList(new ArrayList<>());
        return eventRepository.save(event);
    }


    @Override
    public Event updateEvent(Event event) {
        Event existing = getEventById(event.getEventId());
        existing.setTitle(event.getTitle());
        existing.setDescription(event.getDescription());
        existing.setEventDate(event.getEventDate());
        existing.setParticipantLimit(event.getParticipantLimit());
        return eventRepository.save(existing);
    }

    @Override
    public boolean deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) return false;
        eventRepository.deleteById(eventId);
        return true;
    }

    @Override
    public void joinEvent(Long eventId, Long userId) {
        Event event = getEventById(eventId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (event.getParticipantList().contains(user)) {
            throw new RuntimeException("Already joined");
        }

        if (event.getParticipantList().size() >= event.getParticipantLimit()) {
            throw new RuntimeException("Event is full");
        }

        event.getParticipantList().add(user);
        if (event.getParticipantList().size() == event.getParticipantLimit()) {
            event.setFull(true);
        }

        eventRepository.save(event);
    }

    @Override
    public void leaveEvent(Long eventId, Long userId) {
        Event event = getEventById(eventId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!event.getParticipantList().remove(user)) {
            throw new RuntimeException("User not in event");
        }

        event.setFull(false); // yer açıldı
        eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public List<Event> getAllEventsByUserId(Long userId) {
        return eventRepository.findByUserId(userId);
    }

}
