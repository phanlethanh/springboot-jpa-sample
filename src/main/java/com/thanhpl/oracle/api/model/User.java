package com.thanhpl.oracle.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="M_USER", schema = "public")
public class User {
	
	@Id
	private String userId;
	private String fullName;
	private Date createTime;
	private Date updateTime;
}
