package com.company.interview.employeetask.mapper;

import com.company.interview.employeetask.dto.UserDto;
import com.company.interview.employeetask.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDTO);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> userDtos);
}