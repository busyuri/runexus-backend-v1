package domain.ports;

import domain.models.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    boolean deleteComment(Long commentId, Long userId);
    void likeComment(Long commentId, Long userId);

    List<Comment> getCommentsByEntryId(Long entryId);
}
