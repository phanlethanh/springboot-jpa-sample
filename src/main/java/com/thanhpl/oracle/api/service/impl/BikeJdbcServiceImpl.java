package com.thanhpl.oracle.api.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhpl.oracle.api.dto.BikeProcDto;
import com.thanhpl.oracle.api.dto.GetBikesProcDto;
import com.thanhpl.oracle.api.model.Bike;
import com.thanhpl.oracle.api.service.BikeJdbcService;
import com.thanhpl.oracle.api.utility.LogUtil;

import oracle.jdbc.OracleTypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BikeJdbcServiceImpl implements BikeJdbcService {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public String save(Bike bike, String userId) throws SQLException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareCall("{? = call PKG_ENTITY.F_SAVE_BIKE(?,?,?,?)}");
			stmt.registerOutParameter(1, Types.VARCHAR);
			stmt.setString(2, bike.getName());
			stmt.setString(3, bike.getColor());
			stmt.setString(4, bike.getModel());
			stmt.setString(5, userId);
			
			stmt.execute();
			
			return stmt.getString(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				LogUtil.error(log, e);
			}
		}
	}

	// Get list of bike by producer
	@Override
	public GetBikesProcDto procGetBikes(String userId, String producerId) throws SQLException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareCall("{call PKG_BIKE.P_GET_BIKES(?,?,?,?,?)}");
			stmt.setString(1, userId);
			stmt.setString(2, producerId);
			stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(5, OracleTypes.CURSOR);

			stmt.execute();

			GetBikesProcDto procDto = new GetBikesProcDto();
			String success = stmt.getString(3);
			String responseCode = stmt.getString(4);
			if ("true".equals(success)) {
				ResultSet rs = (ResultSet) stmt.getObject(5);

				if (rs != null) {
					List<BikeProcDto> bikeProcDtos = new ArrayList<BikeProcDto>();
					while (rs.next()) {
						String name = rs.getString("NAME");
						String color = rs.getString("COLOR");
						String model = rs.getString("MODEL");
						Integer age = rs.getInt("AGE");
						String producerName = rs.getString("PRODUCER_NAME");
						String userFullName = rs.getString("USER_FULL_NAME");

						BikeProcDto dto = new BikeProcDto();
						dto.setName(name);
						dto.setColor(color);
						dto.setModel(model);
						dto.setAge(age);
						dto.setProducerName(producerName);
						dto.setUserFullName(userFullName);
					}
					procDto.setBikes(bikeProcDtos);
				}
			}
			procDto.setSuccess(success);
			procDto.setResponseCode(responseCode);

			return procDto;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

}
