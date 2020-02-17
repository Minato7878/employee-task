package com.company.interview.employeetask.mapper;

import com.company.interview.employeetask.dto.SignUpFormDto;
import com.company.interview.employeetask.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SignUpFormMapper {

    SignUpFormDto toDto(UserDto userDto);

    UserDto toEntity(SignUpFormDto signUpFormDTO);

    List<SignUpFormDto> toDtoList(List<UserDto> userDtoList);

    List<UserDto> toEntityList(List<SignUpFormDto> signUpFormDtoList);
}
