package com.popov.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name ="department")
@EqualsAndHashCode(exclude = {"children"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "members")
    private Integer members;

    @Column(name = "archived")
    private Boolean archived;

    @OneToMany(mappedBy = "child")
    private Set<DepartmentTree> children = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                ", archived=" + archived +
                ", children=" + (children!=null ? children.size() : null)  +
                '}';
    }
}
