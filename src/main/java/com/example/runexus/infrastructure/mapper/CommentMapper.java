package com.example.runexus.infrastructure.mapper;

import com.example.runexus.application.dto.CommentInput;
import com.example.runexus.domain.models.Comment;
import com.example.runexus.infrastructure.persistence.entity.CommentEntity;
<<<<<<< HEAD
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
=======
import org.mapstruct.Mapper;
>>>>>>> master

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment entityToDomain(CommentEntity entity);
    CommentEntity domainToEntity(Comment domain);

    Comment inputToDomain(CommentInput input);
}
