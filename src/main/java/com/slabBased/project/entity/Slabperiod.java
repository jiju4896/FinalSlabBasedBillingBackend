package com.slabBased.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_slabperiod")
@Data
public class Slabperiod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Date fromdate;
    private Date todate;


}
