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
@Table(name="M_BIKE", schema = "public")
public class Bike {
	
	@Id
	private String bikeId;
	private String name;
	private String color;
	private String model;
	private String year;
	private String producerId;
	private Date createTime;
	private Date updateTime;
}
