package com.thanhpl.oracle.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhpl.oracle.api.model.Bike;
import com.thanhpl.oracle.api.repository.BikeRepository;
import com.thanhpl.oracle.api.service.BikeService;

@Service
public class BikeServiceImpl implements BikeService {

	@Autowired
	private BikeRepository bikeRepository;
	
	@Override
	public Bike save(Bike bike) {
		return bikeRepository.save(bike);
	}

	@Override
	public List<Bike> getListByModel(String model) {
		return bikeRepository.getListByModel(model);
	}

	@Override
	public void updateName(String bikeId, String name) {
		bikeRepository.updateName(bikeId, name);
	}

}
