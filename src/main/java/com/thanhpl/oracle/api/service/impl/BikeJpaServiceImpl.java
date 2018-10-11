package com.thanhpl.oracle.api.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import com.thanhpl.oracle.api.dto.BikeProcDto;
import com.thanhpl.oracle.api.dto.GetBikesProcDto;
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

	@Override
	public GetBikesProcDto procGetBikes(String userId, String producerId) throws SQLException {
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PKG_BIKE.P_GET_BIKES");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter(5, Class.class, ParameterMode.REF_CURSOR);

			query.setParameter(1, userId);
			query.setParameter(2, producerId);

			query.execute();

			GetBikesProcDto procDto = new GetBikesProcDto();
			String success = (String) query.getOutputParameterValue(3);
			String responseCode = (String) query.getOutputParameterValue(4);
			if ("true".equals(success)) {
				@SuppressWarnings("unchecked")
				List<Object[]> objects = query.getResultList();
				if (objects != null) {
					List<BikeProcDto> bikeProcDtos = new ArrayList<BikeProcDto>();
					int size = objects.size();
					for (int i = 0; i < size; i++) {
						String name = (String) objects.get(i)[0]; // NAME - column 1
						String color = (String) objects.get(i)[1]; // COLOR - column 2
						String model = (String) objects.get(i)[2]; // MODEL - column 3
						Integer age = (Integer) objects.get(i)[3]; // AGE - column 4
						String producerName = (String) objects.get(i)[4]; // PRODUCER_NAME - column 5
						String userFullName = (String) objects.get(i)[5]; // USER_FULL_NAME - column 6

						BikeProcDto dto = new BikeProcDto();
						dto.setName(name);
						dto.setColor(color);
						dto.setModel(model);
						dto.setAge(age);
						dto.setProducerName(producerName);
						dto.setUserFullName(userFullName);
						bikeProcDtos.add(dto);
					}
					procDto.setBikes(bikeProcDtos);
				}
			}
			procDto.setSuccess(success);
			procDto.setResponseCode(responseCode);

			return procDto;
		} catch (Exception e) {
			throw e;
		}
	}

}
