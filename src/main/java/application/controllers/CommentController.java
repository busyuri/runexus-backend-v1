package application.controllers;

import domain.models.Comment;
import domain.ports.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addComment(comment));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId, @RequestParam Long userId) {
        boolean deleted = commentService.deleteComment(commentId, userId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{commentId}/like")
    public ResponseEntity<Void> like(@PathVariable Long commentId, @RequestParam Long userId) {
        commentService.likeComment(commentId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/entry/{entryId}")
    public ResponseEntity<List<Comment>> getCommentsByEntry(@PathVariable Long entryId) {
        return ResponseEntity.ok(commentService.getCommentsByEntryId(entryId));
    }
}

