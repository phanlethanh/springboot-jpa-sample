package com.thanhpl.oracle.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thanhpl.oracle.api.model.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, String> {

	@Query(value = "SELECT * FROM m_bike \r\n" + 
			"WHERE model = :model\r\n" + 
			"ORDER BY create_time DESC\r\n" + 
			"LIMIT 10", 
			nativeQuery = true)
	List<Bike> getListByModel(@Param("model") String model);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_bike\r\n" + 
			"SET name = :name,\r\n" + 
			"	update_time = SYSDATE\r\n" + 
			"WHERE bike_id = :bikeId", nativeQuery = true)
	void updateName(@Param("bikeId") String bikeId, @Param("name") String name);
}
