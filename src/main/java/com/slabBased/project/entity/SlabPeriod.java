package com.slabBased.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;


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


}
