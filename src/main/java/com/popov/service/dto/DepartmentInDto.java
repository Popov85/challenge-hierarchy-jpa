package com.popov.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentInDto {
    private Long id;
    private Long parentId;
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    @Min(1)
    @NotNull
    private Integer members;
    private Boolean archived = false;
}
