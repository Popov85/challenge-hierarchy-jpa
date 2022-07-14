package com.popov.service;

import com.popov.repository.DepartmentRepository;
import com.popov.repository.DepartmentTreeRepository;
import com.popov.repository.entity.Department;
import com.popov.repository.entity.DepartmentTree;
import com.popov.repository.entity.DepartmentTreeId;
import com.popov.service.dto.DepartmentInDto;
import com.popov.service.dto.DepartmentOutDto;
import com.popov.service.mappers.DepartmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    private final DepartmentRepository departmentRepository;

    private final DepartmentTreeRepository departmentTreeRepository;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper,
                                 DepartmentRepository departmentRepository,
                                 DepartmentTreeRepository departmentTreeRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
        this.departmentTreeRepository = departmentTreeRepository;
    }

    @Override
    @Transactional
    public DepartmentOutDto saveDepartment(DepartmentInDto dto) {
        Department entity = departmentRepository.save(departmentMapper.toEntity(dto));
        Long parentId = dto.getParentId();
        if (parentId !=null) {
            addChildDepartment(parentId, entity.getId());
        }
        return departmentMapper.toOutDto(entity);
    }

    @Override
    @Transactional
    public void archiveDepartment(DepartmentInDto dto) {
        throw new UnsupportedOperationException("To be impl.");
    }

    @Override
    @Transactional
    public void addChildDepartment(Long parentId, Long childId) {
        DepartmentTree departmentTree = new DepartmentTree();
        departmentTree.setDepartmentTreeId(new DepartmentTreeId(parentId, childId));
        departmentTree.setDepartment(departmentRepository.findById(parentId)
                .orElseThrow(()->new RuntimeException("Entity not found")));
        departmentTree.setChild(departmentRepository.findById(childId)
                .orElseThrow(()->new RuntimeException("Entity not found")));
        departmentTreeRepository.save(departmentTree);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentOutDto getChildrenDepartments(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        // TODO: if children are not empty - iterate and prepare another bunch!
        return departmentMapper.toOutDto(department);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Department> findAllWithChildrenFromRoot() {
        Set<Department> allWithChildrenFromRoot = departmentTreeRepository.findAllWithChildrenFromRoot();
        log.debug("Top level tree = {}", allWithChildrenFromRoot);
        return Collections.emptySet();
    }
}
