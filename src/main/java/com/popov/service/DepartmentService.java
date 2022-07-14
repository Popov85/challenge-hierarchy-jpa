package com.popov.service;


import com.popov.repository.entity.Department;
import com.popov.service.dto.DepartmentInDto;
import com.popov.service.dto.DepartmentOutDto;

import java.util.Set;

public interface DepartmentService {
    DepartmentOutDto saveDepartment(DepartmentInDto dto);
    void archiveDepartment(DepartmentInDto dto);
    void addChildDepartment(Long parentId, Long childId);
    DepartmentOutDto getChildrenDepartments(Long departmentId);
    Set<Department> findAllWithChildrenFromRoot();
}
