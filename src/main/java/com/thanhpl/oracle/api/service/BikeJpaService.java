package com.thanhpl.oracle.api.service;

import java.sql.SQLException;

import com.thanhpl.oracle.api.model.Bike;

public interface BikeJpaService {
	String save(Bike bike, String userId) throws SQLException;
}
