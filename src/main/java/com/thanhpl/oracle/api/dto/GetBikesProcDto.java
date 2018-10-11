package com.thanhpl.oracle.api.dto;

import java.util.List;

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
public class GetBikesProcDto {
	private String success;
	private String responseCode;
	private List<BikeProcDto> bikes;
}
