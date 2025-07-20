package infrastructure.mapper;

import application.dto.EventInput;
import domain.models.Event;
import infrastructure.persistence.entity.EventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event entityToDomain(EventEntity entity);
    EventEntity domainToEntity(Event domain);

    Event inputToDomain(EventInput input);
}
