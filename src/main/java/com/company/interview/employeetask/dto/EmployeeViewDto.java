package com.company.interview.employeetask.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeViewDto {

    Long id;

    @NotNull
    String name;

    @NotNull
    Boolean isActive;

    String departmentName;

}
