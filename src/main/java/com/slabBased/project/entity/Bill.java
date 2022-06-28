package com.slabBased.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_bill_storage")

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


    public Bill(Long id, Double currentRead, Double netUnit, Double slabRate, Double billAmount) {
        this.id = id;
        this.currentRead = currentRead;
        this.netUnit = netUnit;
        this.slabRate = slabRate;
        this.billAmount = billAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Double getCurrentRead() {
        return currentRead;
    }

    public void setCurrentRead(Double currentRead) {
        this.currentRead = currentRead;
    }


    public void setNetUnit(Double netUnit) {
        this.netUnit = netUnit;
    }


    public void setSlabRate(Double slabRate) {
        this.slabRate = slabRate;
    }


    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getNetUnit() {
        return netUnit;
    }

    public Double getSlabRate() {
        return slabRate;
    }

    public Double getBillAmount() {
        return billAmount;
    }
}
