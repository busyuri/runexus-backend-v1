package infrastructure.persistence.repositories;

import domain.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
List<Comment> findByEntryId(Long entryId);

}
