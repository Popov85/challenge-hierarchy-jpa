package com.popov.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentOutDto {
    private Long id;
    private Long parentId;
    private String name;
    private Integer members;
    private Boolean archived = false;
}
