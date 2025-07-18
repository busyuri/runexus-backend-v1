package domain.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long userId;
    private Long entryId;
    private String commentText;
    private LocalDateTime commentDate;
    private int likeCount;
    // varsayım: sadece entrylere comment yazılabilir

    public Comment() {
        this.commentDate = LocalDateTime.now();
        this.likeCount = 0;
    }
    public Comment(Long commentId, Long userId, Long entryId, String commentText, int likeCount) {
        this.commentId = commentId;
        this.userId = userId;
        this.entryId = entryId;
        this.commentText = commentText;
        this.commentDate = LocalDateTime.now();
        this.likeCount = 0;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
