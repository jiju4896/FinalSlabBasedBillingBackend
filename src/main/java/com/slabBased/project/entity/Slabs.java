package com.slabBased.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_slabs")
@Data
public class Slabs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Double startread;
    private Double endread;
    private Double value;
    private Long slabid;

}
