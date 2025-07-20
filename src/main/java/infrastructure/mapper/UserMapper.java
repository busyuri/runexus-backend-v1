package infrastructure.mapper;

import application.dto.UserInput;
import infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    domain.models.User entityToDomain(UserEntity entity);
    UserEntity domainToEntity(domain.models.User domain);

    domain.models.User inputToDomain(UserInput input);
}
