package infrastructure.mapper;

import application.dto.CommentInput;
import domain.models.Comment;
import infrastructure.persistence.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment entityToDomain(CommentEntity entity);
    CommentEntity domainToEntity(Comment domain);

    Comment inputToDomain(CommentInput input);
}
