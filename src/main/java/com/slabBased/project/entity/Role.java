package com.slabBased.project.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_description")
    private String roleDescription;
}
