package com.slabBased.project.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(name="username",unique = true, nullable = false)
	private String username;
}
