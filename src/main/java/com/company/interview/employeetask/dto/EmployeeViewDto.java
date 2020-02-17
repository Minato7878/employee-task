package com.company.interview.employeetask.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeViewDto {

    Long id;

    String name;

    Boolean isActive;

    String departmentName;

}
