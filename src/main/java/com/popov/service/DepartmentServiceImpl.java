package com.popov.service;

import com.popov.repository.DepartmentRepository;
import com.popov.repository.entity.Department;
import com.popov.service.dto.DepartmentInDto;
import com.popov.service.dto.DepartmentOutDto;
import com.popov.service.dto.DepartmentOutInvertedL0Dto;
import com.popov.service.mappers.DepartmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper,
                                 DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public DepartmentOutDto saveDepartment(DepartmentInDto dto) {
        Department department = departmentMapper.toEntity(dto);
        Department result = departmentRepository.save(department);
        return departmentMapper.toDto(result);
    }

    @Override
    public DepartmentOutDto archiveDepartment(Long departmentId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    @Transactional(readOnly = true)
    public Set<DepartmentOutInvertedL0Dto> findSubTreeFromRoot() {
        Set<Department> depWithChildren = departmentRepository.findAllWithChildrenFromRoot();
        //log.debug("Entity Sub-tree from root = {}",depWithChildren);
        Set<DepartmentOutInvertedL0Dto> departmentOutInvertedDto =
                departmentMapper.toInvertedDto(depWithChildren);
        //log.debug("Dto Sub-tree from root = {}", departmentOutInvertedDto);
        return departmentOutInvertedDto;
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentOutInvertedL0Dto findSubTreeById(Long departmentId) {
        Department depWithChildren = departmentRepository.findOneWithChildrenById(departmentId);
        //log.debug("Entity Sub-tree from depId = {} = {}", departmentId, depWithChildren);
        DepartmentOutInvertedL0Dto departmentOutInvertedDto =
                departmentMapper.toInvertedDto(depWithChildren);
        //log.debug("DTO Sub-tree from depId = {} = {}", departmentId, departmentOutInvertedDto);
        return departmentOutInvertedDto;
    }
}
