package application.controllers;

import infrastructure.mapper.CommentMapper;
import infrastructure.persistence.entity.CommentEntity;
import domain.ports.CommentService;
import infrastructure.persistence.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceImpl commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentServiceImpl commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    public ResponseEntity<List<Comment>>
}

