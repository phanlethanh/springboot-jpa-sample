package com.thanhpl.oracle.api.service;

import java.util.List;

import com.thanhpl.oracle.api.model.Bike;

public interface BikeService {
	Bike save(Bike bike);
	
	List<Bike> getListByModel(String model);
	
	void updateName(String bikeId, String name);
}
