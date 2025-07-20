package infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long entryId;
    private String content;
    private LocalDateTime commentDate;
    private int likeCount;
    // varsayım: sadece entrylere comment yazılabilir

    public CommentEntity() {
        this.commentDate = LocalDateTime.now();
        this.likeCount = 0;
    }

    public CommentEntity(Long id, Long userId, Long entryId, String content, int likeCount) {
        this.id = id;
        this.userId = userId;
        this.entryId = entryId;
        this.content = content;
        this.commentDate = LocalDateTime.now();
        this.likeCount = 0; // constta verip neden sonra 0
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String commentText) {
        this.content = commentText;
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
