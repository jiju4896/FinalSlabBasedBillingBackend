package com.slabBased.project.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.slabBased.project.utils.ConstantUtil.*;

@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    @Pattern(regexp = firstNameRegex, message = "FirstName should Contain Alphabets Only")
    private String firstName;
    @Column(name = "last_name")
    @Pattern(regexp = lastNameRegex, message = "LastName should Contain Alphabets Only")
    private String lastName;

    @Pattern(regexp = emailRegex, message = "Please Provide a Valid Email")
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "user_name", unique = true, nullable = false)
    @Pattern(regexp = userNameRegex, message = "username must be of 6 to 12 length with no special characters")
    private String userName;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Bill> bill = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tbl_user_role", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})

    private Set<Role> roles = new HashSet<>();

    public User(Long id, String firstName, String lastName, String email, String password, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public List<Bill> getBill() {
        return bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
