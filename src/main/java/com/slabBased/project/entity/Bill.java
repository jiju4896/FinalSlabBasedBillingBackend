package com.slabBased.project.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_billstorage")
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;
	private String premonth;
	private String curmonth;
	private Long preread;
	private Long curread;
	private Long netunit;
	private int slabrate;
	private Long billamount;
	@Column(name = "username")
	private String username;
}
