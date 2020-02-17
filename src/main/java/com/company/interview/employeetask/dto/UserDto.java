package com.company.interview.employeetask.dto;

import com.company.interview.employeetask.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;

    @NotNull
    String login;

    @NotNull
    String password;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    String email;

    @NotNull
    Role role;

}