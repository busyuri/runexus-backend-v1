package infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "events")

public class EventEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private Long userId;
    private String title;
    private String description;
    private int participantLimit;
    private boolean isFull;
    private LocalDate eventDate;

    @Transient
    private List<UserEntity> participantList;

    public EventEntity() {
        this.isFull = false;
    }

    public EventEntity(Long eventId, Long userId, String title, String description, int participantLimit, LocalDate eventDate) {
        this.eventId = eventId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.participantLimit = participantLimit;
        this.isFull = false;
        this.eventDate = eventDate;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventid) {
        this.eventId = eventid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userid) {
        this.userId = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(int participantLimit) {
        this.participantLimit = participantLimit;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public List<UserEntity> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<UserEntity> participantList) {
        this.participantList = participantList;
    }
}
