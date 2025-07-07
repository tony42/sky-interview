package cz.tony.skyinterview.mapper;

import static org.junit.jupiter.api.Assertions.*;

import cz.tony.skyinterview.entity.User;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    @Test
    void userToUserDto() {
        User user = new User();
        user.setEmail("test@user.com");
        user.setName("Test User");

        var userDto = UserMapper.INSTANCE.userToUserDto(user);

        assertNotNull(userDto);
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getName(), userDto.getName());
    }

}