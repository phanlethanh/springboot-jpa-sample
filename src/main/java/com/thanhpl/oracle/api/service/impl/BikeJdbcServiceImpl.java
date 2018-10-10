package com.thanhpl.oracle.api.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhpl.oracle.api.dto.BikeDto;
import com.thanhpl.oracle.api.model.Bike;
import com.thanhpl.oracle.api.service.BikeJdbcService;
import com.thanhpl.oracle.api.utility.LogUtil;

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

	@Override
	public List<BikeDto> procGetBikes(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
