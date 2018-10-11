package com.thanhpl.oracle.api.dto;

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
public class BikeProcDto {
	private String name;
	private String color;
	private String model;
	private Integer age;
	private String producerName;
	private String userFullName;
}
