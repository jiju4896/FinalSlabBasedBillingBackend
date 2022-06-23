package com.slabBased.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "tbl_slab_period")

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlabPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Slabs> slabsSet = new HashSet<>();

    public SlabPeriod(Long id, Date fromDate, Date toDate) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }


    public Date getToDate() {
        return toDate;
    }


    public Set<Slabs> getSlabsSet() {
        return slabsSet;
    }


}
