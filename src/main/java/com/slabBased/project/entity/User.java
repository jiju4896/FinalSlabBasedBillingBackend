package com.slabBased.project.entity;
import javax.persistence.*;

import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "tbl_user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(name="user_name",unique = true, nullable = false)
	private String userName;
	@ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name ="tbl_user_role",joinColumns = {@JoinColumn (name = "USER_ID")},inverseJoinColumns = {@JoinColumn(name="ROLE_ID")})
	private Set<Role> roles;
}
