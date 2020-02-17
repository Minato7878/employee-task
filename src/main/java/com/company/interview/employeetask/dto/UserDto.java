package com.company.interview.employeetask.dto;

import com.company.interview.employeetask.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;

    String login;

    String password;

    String firstName;

    String lastName;

    String email;

    Role role;

}