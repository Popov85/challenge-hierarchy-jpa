package com.popov.repository;

import com.popov.repository.entity.Department;
import com.popov.repository.entity.DepartmentTree;
import com.popov.repository.entity.DepartmentTreeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DepartmentTreeRepository extends JpaRepository<DepartmentTree, DepartmentTreeId> {

    @Query(value = "SELECT t.department FROM DepartmentTree t where " +
            "NOT EXISTS (SELECT dt FROM DepartmentTree dt WHERE t.department.id = dt.child.id)")
    Set<Department> findAllWithChildrenFromRoot();
}
