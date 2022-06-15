package com.slabBased.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="tbl_slab_period")
@Data
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
    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name ="tbl_slab_period_slabs",joinColumns = {@JoinColumn (name = "SLAB_PERIOD_ID")},inverseJoinColumns = {@JoinColumn(name="SLABS_ID")})
private Set<Slabs> slabsSet;

}
