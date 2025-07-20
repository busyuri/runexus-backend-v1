package domain.ports;

import domain.models.Comment;
import infrastructure.persistence.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentByEntryId(Long entryId);

    Comment createComment(Comment comment);
    boolean deleteComment(Long commentId, Long userId);
    void likeComment(Long commentId, Long userId);


}
