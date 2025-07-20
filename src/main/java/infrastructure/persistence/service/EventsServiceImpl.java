package infrastructure.persistence.service;


import domain.models.Event;
import domain.ports.EventService;
import infrastructure.mapper.EventMapper;
import infrastructure.persistence.entity.EventEntity;
import infrastructure.persistence.entity.UserEntity;
import infrastructure.persistence.repository.EventRepository;
import infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventsServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;

    public EventsServiceImpl(EventRepository eventRepository, UserRepository userRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventMapper = eventMapper;
    }


    @Override
    public Event createEvent(Event event) {
        EventEntity saved = eventRepository.save(eventMapper.domainToEntity(event));
        return eventMapper.entityToDomain(saved);
    }

    @Override
    public Event updateEvent(Long eventId, Event event) {
        EventEntity updatedEventEntity = eventMapper.domainToEntity(event);
        updatedEventEntity.setEventId(eventId);
        return eventMapper.entityToDomain(eventRepository.save(updatedEventEntity));
    }

    @Override
    public boolean deleteEvent(Long eventId, Long userId) {
        Optional<EventEntity> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            EventEntity event = optionalEvent.get();

            if (!event.getUserId().equals(userId)) {
                return false;
            }

            eventRepository.deleteById(eventId);
            return true;
        }
        return false;
    }

    @Override
    public void joinEvent(Long eventId, Long userId) {
        Optional<EventEntity> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) return;

        EventEntity event = optionalEvent.get();

        if (event.isFull()) return;

        // Katılımcı listesi null olabilir, kontrol et
        List<UserEntity> participantList = event.getParticipantList();
        if (participantList == null) {
            throw new IllegalStateException("Participant list is not initialized.");
        }

        boolean alreadyJoined = participantList.stream()
                .anyMatch(user -> user.getUserId().equals(userId));
        if (alreadyJoined) return;

        // Gerçek kullanıcıyı userRepository üzerinden al
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return;

        UserEntity realUser = optionalUser.get();
        participantList.add(realUser);

        if (participantList.size() >= event.getParticipantLimit()) {
            event.setFull(true);
        }

        eventRepository.save(event);
    }


    @Override
    public void leaveEvent(Long eventId, Long userId) {
        Optional<EventEntity> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) return;

        EventEntity event = optionalEvent.get();

        List<UserEntity> participantList = event.getParticipantList();
        if (participantList == null) return;

        boolean removed = participantList.removeIf(user -> user.getUserId().equals(userId));

        if (removed) {
            // Katılımcı sayısı artık limitin altındaysa, full flag'i sıfırlanır
            if (participantList.size() < event.getParticipantLimit()) {
                event.setFull(false);
            }

            eventRepository.save(event);
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .map(eventMapper::entityToDomain);
    }

    @Override
    public List<Event> getEventByUserId(Long userId) {
        return eventRepository.findByUserId(userId)
                .stream()
                .map(eventMapper::entityToDomain)
                .collect(Collectors.toList());
    }

}
