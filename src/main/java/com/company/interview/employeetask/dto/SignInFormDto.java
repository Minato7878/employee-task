package com.company.interview.employeetask.dto;

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
public class SignInFormDto {

    @NotNull
    String login;

    @NotNull
    String password;

}