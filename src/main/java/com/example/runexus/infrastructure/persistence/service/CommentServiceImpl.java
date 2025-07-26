package com.example.runexus.infrastructure.persistence.service;

import com.example.runexus.domain.models.Comment;
import com.example.runexus.domain.ports.CommentService;
import com.example.runexus.infrastructure.mapper.*;
import com.example.runexus.infrastructure.persistence.entity.*;
import com.example.runexus.infrastructure.persistence.exceptions.CannotLikeOwnCommentException;
import com.example.runexus.infrastructure.persistence.exceptions.CommentAlreadyLikedException;
import com.example.runexus.infrastructure.persistence.exceptions.ElementNotFoundException;
import com.example.runexus.infrastructure.persistence.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
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
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ElementNotFoundException());

        if (comment.getUserId().equals(userId)) {
            throw new CannotLikeOwnCommentException();
        }

        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return;

        UserEntity user = optionalUser.get();
        if (comment.getLikedUsers().contains(user)) {
            throw new CommentAlreadyLikedException();
        }

        comment.getLikedUsers().add(user);
        comment.setLikeCount(comment.getLikeCount() + 1);
        commentRepository.save(comment);
    }


}
