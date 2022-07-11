package com.popov.repository;

import com.popov.repository.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "SELECT d FROM Department d " +
            "left join fetch d.children c left join fetch c.children cc where d.parent is null order by d.name ASC")
    Set<Department> findAllWithChildrenFromRoot();

    @Query(value = "SELECT d FROM Department d " +
            "left join fetch d.children c left join fetch c.children cc where d.id=?1")
    Department findOneWithChildrenById(Long departmentId);

}
