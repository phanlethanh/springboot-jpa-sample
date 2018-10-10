package com.thanhpl.oracle.api.service.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.thanhpl.oracle.api.model.Bike;
import com.thanhpl.oracle.api.service.BikeJpaService;

@Service
public class BikeJpaServiceImpl implements BikeJpaService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String save(Bike bike, String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
