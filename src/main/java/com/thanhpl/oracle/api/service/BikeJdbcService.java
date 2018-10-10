package com.thanhpl.oracle.api.service;

import java.sql.SQLException;
import java.util.List;

import com.thanhpl.oracle.api.dto.BikeDto;
import com.thanhpl.oracle.api.model.Bike;

public interface BikeJdbcService {

	String save(Bike bike, String userId) throws SQLException;
	
	List<BikeDto> procGetBikes(String userId);
}
