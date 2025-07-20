package infrastructure.mapper;

import application.dto.EntryInput;
import domain.models.Entry;
import infrastructure.persistence.entity.EntryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntryMapper {

    Entry entityToDomain(EntryEntity entity);
    EntryEntity domainToEntity(Entry domain);

    Entry inputToDomain(EntryInput input);
}
