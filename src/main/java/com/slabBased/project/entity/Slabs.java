package com.slabBased.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "tbl_slabs")

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getStartRead() {
        return startRead;
    }

    public void setStartRead(Double startRead) {
        this.startRead = startRead;
    }

    public Double getEndRead() {
        return endRead;
    }

    public void setEndRead(Double endRead) {
        this.endRead = endRead;
    }

    public Double getSlabRate() {
        return slabRate;
    }

    public void setSlabRate(Double slabRate) {
        this.slabRate = slabRate;
    }


}
