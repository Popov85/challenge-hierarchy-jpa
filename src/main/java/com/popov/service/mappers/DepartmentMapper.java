package com.popov.service.mappers;

import com.popov.repository.entity.Department;
import com.popov.service.dto.DepartmentInDto;
import com.popov.service.dto.DepartmentOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

    DepartmentInDto toDto(Department entity);

    DepartmentOutDto toOutDto(Department entity);

    Set<DepartmentOutDto> toOutDto(Set<Department> entity);

    @Mapping(target = "archived", defaultValue = "false")
    Department toEntity(DepartmentInDto entity);
}
