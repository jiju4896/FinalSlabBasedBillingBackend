package com.slabBased.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "tbl_bill_storage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private Long id;

    @Column(name = "bill_date")

    private Date billDate;
    @Column(name = "current_read")
    private Double currentRead;
    @Column(name = "net_unit")
    private Double netUnit;
    @Column(name = "slab_rate")
    private Double slabRate;
    @Column(name = "bill_amount")
    private Double billAmount;
    @Column(name = "user_id")
    private Long userId;
}
