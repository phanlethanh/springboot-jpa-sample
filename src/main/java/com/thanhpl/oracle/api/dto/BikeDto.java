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
public class BikeDto {
	private String bikeName;
	private String userFullName;
	private Integer bikeAge;
}
