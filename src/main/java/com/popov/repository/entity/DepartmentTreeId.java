package com.popov.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentTreeId implements Serializable {
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "child_id")
    private Long childId;
}
