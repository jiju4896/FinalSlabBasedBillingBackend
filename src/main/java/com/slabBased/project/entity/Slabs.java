package com.slabBased.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="tbl_slabs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Slabs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_read")
    private Double startRead;
    @Column(name = "end_read")
    private Double endRead;
    @Column(name = "slab_rate")
    private Double slabRate;
    @Column(name = "slab_period_id")
    private Long slabId;

}
