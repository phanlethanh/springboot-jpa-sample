package com.thanhpl.oracle.api.response;

import java.util.List;

import com.thanhpl.oracle.api.dto.BikeProcDto;

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
public class GetBikesResponseData {
	private List<BikeProcDto> bikes;
}
