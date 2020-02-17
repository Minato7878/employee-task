package com.company.interview.employeetask.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {

    Long id;

    @NotNull
    String name;

    @NotNull
    Boolean isActive;

    Long departmentId;
}
