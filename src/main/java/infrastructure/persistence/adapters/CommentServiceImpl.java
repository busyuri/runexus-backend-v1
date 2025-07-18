package infrastructure.persistence.adapters;

import domain.models.Comment;
import domain.ports.CommentService;
import infrastructure.persistence.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        comment.setCommentDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteComment(Long commentId, Long userId) {
        Comment comment= commentRepository.findById(commentId)
                .orElse(null);

        if(comment!=null && comment.getUserId().equals(userId)) {
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

    @Override
    public void likeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new RuntimeException("Comment not found"));

        comment.setLikeCount(comment.getLikeCount()+1);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByEntryId(Long entryId) {
        return commentRepository.findByEntryId(entryId);
    }


}
