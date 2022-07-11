package com.popov.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name ="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Department parent;

    @OrderBy("name ASC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Department> children;

    @Column(name = "name")
    private String name;

    @Column(name = "members")
    private Integer members;

    @Column(name = "archived")
    private Boolean archived;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", parent=" + (parent !=null ?
                        String.valueOf(parent.getId()) : null) +
                //", children='" + children + '\'' +
                ", name='" + name + '\'' +
                ", members=" + members +
                ", archived=" + archived +
                '}';
    }
}
