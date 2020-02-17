package com.company.interview.employeetask.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpFormDto {

    String login;

    String firstName;

    String lastName;

    String password;
    
    String email;
}