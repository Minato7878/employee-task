package com.company.interview.employeetask.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

    Long id;

    @NotNull
    String name;

    @NotNull
    Boolean isActive;

    Long departmentId;

}
