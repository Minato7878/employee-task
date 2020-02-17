package com.company.interview.employeetask.mapper;

import com.company.interview.employeetask.dto.SignInFormDto;
import com.company.interview.employeetask.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SignInFormMapper {

    SignInFormDto toDto(UserDto userDto);

    UserDto toEntity(SignInFormDto signInFormDTO);

    List<SignInFormDto> toDtoList(List<UserDto> userDtoList);

    List<UserDto> toEntityList(List<SignInFormDto> signInFormDtoList);
}
