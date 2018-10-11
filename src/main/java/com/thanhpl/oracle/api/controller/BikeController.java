package com.thanhpl.oracle.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thanhpl.oracle.api.dto.GetBikesProcDto;
import com.thanhpl.oracle.api.model.Bike;
import com.thanhpl.oracle.api.request.GetBikesRequest;
import com.thanhpl.oracle.api.request.SaveBikeRequest;
import com.thanhpl.oracle.api.response.GetBikesResponse;
import com.thanhpl.oracle.api.response.GetBikesResponseData;
import com.thanhpl.oracle.api.response.SaveBikeResponse;
import com.thanhpl.oracle.api.response.SaveBikeResponseData;
import com.thanhpl.oracle.api.service.BikeJdbcService;
import com.thanhpl.oracle.api.service.BikeJpaService;
import com.thanhpl.oracle.api.service.BikeService;
import com.thanhpl.oracle.api.utility.JsonUtil;
import com.thanhpl.oracle.api.utility.LogUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {
		"http://localhost:3000",
		"http://thanhpl.com"
		}, maxAge = 3600)
@RestController
public class BikeController {
	
	@Autowired
	private BikeService bikeService;
	
	@Autowired
	private BikeJpaService bikeJpaService;
	
	@Autowired
	private BikeJdbcService bikeJdbcService;
	
	@RequestMapping(value = "/bike/save", method = RequestMethod.POST)
	public SaveBikeResponse savePost(@RequestBody SaveBikeRequest request) {
		log.info(JsonUtil.toJson(request));
		SaveBikeResponse response = new SaveBikeResponse();
		SaveBikeResponseData data = new SaveBikeResponseData();
		
		Bike bike = new Bike();
		bike.setBikeId("1");
		bike.setName(request.getName());
		bike.setColor(request.getColor());
		bike.setModel(request.getModel());
		
		try {
			bike = bikeService.save(bike);
			if (bike != null) {
				data.setId(bike.getBikeId());
				response.setData(data);
				response.setSuccess(true);
				response.setCode("0");
				response.setMessage("Success");
			} else {
				response.setSuccess(false);
				response.setCode("900");
				response.setMessage("System error");
			}
		} catch (Exception e) {
			LogUtil.error(log, e);
		}
		
		log.info(JsonUtil.toJson(response));
		return response;
	}
	
	@RequestMapping(value = "/bikes/jpa", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/bikes/jdbc", method = RequestMethod.POST)
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
