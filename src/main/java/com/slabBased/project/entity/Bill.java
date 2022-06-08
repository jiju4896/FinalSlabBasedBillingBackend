package com.slabBased.project.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tbl_billstorage")
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;
	private Date currentdate;
	private Double curread;
	private Double netunit;
	private Double slabrate;
	private Double billamount;
	private Long userid;
}
