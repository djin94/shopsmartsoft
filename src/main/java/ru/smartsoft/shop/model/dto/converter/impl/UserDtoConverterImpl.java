package ru.smartsoft.shop.model.dto.converter.impl;

import ru.smartsoft.shop.model.dto.UserDto;
import ru.smartsoft.shop.model.dto.converter.UserDtoConverter;
import ru.smartsoft.shop.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDtoConverterImpl implements UserDtoConverter {
    @Override
    public UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setLogin(user.getLogin());
        userDto.setRole(user.getRole());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @Override
    public List<UserDto> convertToUserDtos(List<User> users) {
        return users.stream().collect(ArrayList::new, (userDtos, user) -> userDtos.add(convertToUserDto(user)), ArrayList::addAll);
    }
}
