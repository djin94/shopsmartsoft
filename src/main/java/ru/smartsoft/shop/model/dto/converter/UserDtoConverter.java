package ru.smartsoft.shop.model.dto.converter;

import ru.smartsoft.shop.model.dto.UserDto;
import ru.smartsoft.shop.model.entity.User;

import java.util.List;

public interface UserDtoConverter {
    UserDto convertToUserDto(User user);

    List<UserDto> convertToUserDtos(List<User> users);
}
