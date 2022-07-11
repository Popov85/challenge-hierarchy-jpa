package com.popov.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentOutInvertedL0Dto {
    private Long id;
    private Long parentId;
    private Set<DepartmentOutInvertedL1Dto> children = new HashSet<>();
    private String name;
    private Integer members;
    private Boolean archived = false;
}
