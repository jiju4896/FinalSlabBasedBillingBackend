package com.slabBased.project.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="token_table")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;
    private String tokenCopy;
}
