package com.popov.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="department_tree")
public class DepartmentTree {

    @EmbeddedId
    private DepartmentTreeId departmentTreeId = new DepartmentTreeId();

    @MapsId("departmentId")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @MapsId("childId")
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Department child;

    @Override
    public String toString() {
        return "DepartmentTree{" +
                "departmentTreeId=" + departmentTreeId +
                '}';
    }
}
