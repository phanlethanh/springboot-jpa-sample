package com.thanhpl.oracle.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thanhpl.oracle.api.dto.GetBikesProcDto;
import com.thanhpl.oracle.api.request.GetBikesRequest;
import com.thanhpl.oracle.api.response.GetBikesResponse;
import com.thanhpl.oracle.api.response.GetBikesResponseData;
import com.thanhpl.oracle.api.service.BikeJdbcService;
import com.thanhpl.oracle.api.service.BikeJpaService;
import com.thanhpl.oracle.api.utility.JsonUtil;
import com.thanhpl.oracle.api.utility.LogUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {
		"http://localhost:3000",
		"http://thanhpl.com"
		}, maxAge = 3600)
@RestController
@RequestMapping(value = "/bikes")
public class GetBikesController {

	@Autowired
	private BikeJpaService bikeJpaService;
	
	@Autowired
	private BikeJdbcService bikeJdbcService;
	
	@RequestMapping(value = "/jpa", method = RequestMethod.POST)
	public GetBikesResponse getBikesInJpa(@RequestBody GetBikesRequest request) {
		log.info(JsonUtil.toJson(request));
		GetBikesResponse response = new GetBikesResponse();
		GetBikesResponseData data = new GetBikesResponseData();
		
		try {
			GetBikesProcDto procDto = bikeJpaService.procGetBikes(
					request.getUserId(), request.getProducerId());
			if (procDto != null) {
				String success = procDto.getSuccess();
				if ("true".equals(success)) {
					response.setSuccess(true);
					response.setCode("0");
					data.setBikes(procDto.getBikes());
				} else {
					response.setSuccess(false);
					response.setCode(procDto.getResponseCode());
				}
			} else {
				response.setSuccess(false);
				response.setCode("900");
			}
			response.setData(data);
		} catch (Exception e) {
			LogUtil.error(log, e);
		}
		
		log.info(JsonUtil.toJson(response));
		return response;
	}
	
	@RequestMapping(value = "/jdbc", method = RequestMethod.POST)
	public GetBikesResponse getBikesInJdbc(@RequestBody GetBikesRequest request) {
		log.info(JsonUtil.toJson(request));
		GetBikesResponse response = new GetBikesResponse();
		GetBikesResponseData data = new GetBikesResponseData();
		
		try {
			GetBikesProcDto procDto = bikeJdbcService.procGetBikes(
					request.getUserId(), request.getProducerId());
			if (procDto != null) {
				String success = procDto.getSuccess();
				if ("true".equals(success)) {
					response.setSuccess(true);
					response.setCode("0");
					data.setBikes(procDto.getBikes());
				} else {
					response.setSuccess(false);
					response.setCode(procDto.getResponseCode());
				}
			} else {
				response.setSuccess(false);
				response.setCode("900");
			}
			response.setData(data);
		} catch (Exception e) {
			LogUtil.error(log, e);
		}
		
		log.info(JsonUtil.toJson(response));
		return response;
	}
}
