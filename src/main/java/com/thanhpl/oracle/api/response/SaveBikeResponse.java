package com.thanhpl.oracle.api.response;

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
public class SaveBikeResponse extends BaseResponse {
	private SaveBikeResponseData data;
}
