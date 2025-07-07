package cz.tony.skyinterview.mapper;

import cz.tony.skyinterview.dto.UserDto;
import cz.tony.skyinterview.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Antonin.Karasek
 * @since 2025-09-17
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

}
