package com.popov.service;

import com.popov.service.dto.DepartmentInDto;
import com.popov.service.dto.DepartmentOutDto;
import com.popov.service.dto.DepartmentOutInvertedL0Dto;

import java.util.Set;

public interface DepartmentService {
    DepartmentOutDto saveDepartment(DepartmentInDto dto);
    DepartmentOutDto archiveDepartment(Long departmentId);
    Set<DepartmentOutInvertedL0Dto> findSubTreeFromRoot();
    DepartmentOutInvertedL0Dto findSubTreeById(Long departmentId);
}
