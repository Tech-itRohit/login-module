package com.log.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="user_table")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 private int id;
 @Column(name = "Email", nullable = false)
 private String email;
 @Column(name = "{Password", nullable = false)
 private String password;
 private String role;
}

