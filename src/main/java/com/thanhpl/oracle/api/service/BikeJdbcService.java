package com.thanhpl.oracle.api.service;

import java.sql.SQLException;

import com.thanhpl.oracle.api.dto.GetBikesProcDto;
import com.thanhpl.oracle.api.model.Bike;

public interface BikeJdbcService {

	String save(Bike bike, String userId) throws SQLException;
	
	GetBikesProcDto procGetBikes(String userId, String producerId) throws SQLException;
}
