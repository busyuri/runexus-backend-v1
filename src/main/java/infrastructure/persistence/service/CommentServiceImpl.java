package infrastructure.persistence.service;

import domain.models.Comment;
import infrastructure.mapper.CommentMapper;
import infrastructure.persistence.entity.CommentEntity;
import domain.ports.CommentService;
import infrastructure.persistence.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> findCommentByEntryId(Long entryId) {
        return commentRepository.findByEntryId(entryId)
                .stream()
                .map(commentMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Comment createComment(Comment comment) {
        CommentEntity savedCommentEntity = commentRepository.save(commentMapper.domainToEntity(comment));
        return commentMapper.entityToDomain(savedCommentEntity);
    }

    @Override
    public boolean deleteComment(Long commentId, Long userId) {
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isPresent()) {
            CommentEntity comment = optionalComment.get();

            if (!comment.getUserId().equals(userId)) {
                // yetkisiz kullanıcı denemesi
                return false;
            }

            commentRepository.deleteById(commentId);
            return true;

        } else {
            // yorum yoksa
            return false;
        }
    }


    @Override
    public void likeComment(Long commentId, Long userId) {
        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isPresent()) {
            CommentEntity comment = optionalComment.get();

            if (comment.getUserId().equals(userId)) {
                return;
            }

            int currentLikes = comment.getLikeCount();
            comment.setLikeCount(currentLikes + 1);

            commentRepository.save(comment);
        }

    }
}
