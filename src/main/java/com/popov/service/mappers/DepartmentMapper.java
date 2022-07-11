package com.popov.service.mappers;

import com.popov.repository.entity.Department;
import com.popov.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ReferenceMapper.class})
public interface DepartmentMapper {
    @Mapping(target = "parentId", source = "parent.id")
    DepartmentOutDto toDto(Department entity);
    @Mapping(target = "parentId", source = "parent.id")
    DepartmentOutInvertedL0Dto toInvertedDto(Department entity);
    @Mapping(target = "parentId", source = "parent.id")
    DepartmentOutInvertedL1Dto toInverted1Dto(Department entity);
    @Mapping(target = "parentId", source = "parent.id")
    DepartmentOutInvertedL2Dto toInverted2Dto(Department entity);
    @Mapping(target = "parentId", source = "parent.id")
    Set<DepartmentOutInvertedL0Dto> toInvertedDto(Set<Department> entity);
    @Mapping(target = "archived", defaultValue = "false")
    @Mapping(target = "parent", source = "parentId")
    Department toEntity(DepartmentInDto entity);
    Department toEntity(Long departmentId);

}
