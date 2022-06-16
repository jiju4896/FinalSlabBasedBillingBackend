package com.slabBased.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="tbl_slab_period")

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
    @ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name ="tbl_slab_period_slabs",joinColumns = {@JoinColumn (name = "SLAB_PERIOD_ID")},inverseJoinColumns = {@JoinColumn(name="SLABS_ID")})
private Set<Slabs> slabsSet=new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Set<Slabs> getSlabsSet() {
        return slabsSet;
    }

    public void setSlabsSet(Set<Slabs> slabsSet) {
        this.slabsSet = slabsSet;
    }

}
