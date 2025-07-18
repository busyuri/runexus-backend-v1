package domain.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name= "entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    private Long userId;
    private String entryTitle;
    private String entryDescription;
    private LocalDate entryDate;

    @Transient
    private List<Comment> commentList;


    public Entry() {
        this.entryDate = LocalDate.now();
    }

    public Entry(Long entryId, Long userId, String entryTitle, String entryDescription, LocalDate entryDate) {
        this.entryId = entryId;
        this.userId = userId;
        this.entryTitle = entryTitle;
        this.entryDescription = entryDescription;
        this.entryDate = LocalDate.now();
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
