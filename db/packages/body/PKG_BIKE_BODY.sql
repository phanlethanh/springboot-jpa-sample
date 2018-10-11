CREATE OR REPLACE PACKAGE BODY PKG_BIKE AS
   
PROCEDURE P_GET_BIKES(
		i_user_id IN VARCHAR2,
		i_producer_id IN VARCHAR2, 
		o_success OUT VARCHAR2,
		o_response_code OUT VARCHAR2,
		o_bike_cursor OUT SYS_REFCURSOR)) IS
	
	w_index PLS_INTEGER := 0;
	w_bike_arr BIKE_TYPE_ARR;
	w_age NUMBER;
	w_current_year NUMBER;

	CURSOR bike_cur(c_user_id IN VARCHAR2, c_producer_id IN VARCHAR2) IS
		SELECT b.name AS name,
			b.color AS color,
			b.model AS model,
			b.year AS year,
			p.producer_name AS producer_name,
			u.full_name AS user_full_name
		FROM M_BIKE b
		JOIN M_PRODUCER p ON (b.producer_id = p.producer_id)
		JOIN M_USER_BIKE ub ON (ub.bike_id = b.bike_id)
		JOIN M_USER u ON (ub.user_id = u.user_id)
		WHERE ub.user_id = c_user_id
			AND b.producer_id = producer_id
			ORDER BY b.create_time DESC;

	bike_rec bike_cur%ROWTYPE;

BEGIN
	
	o_success := 'true';
	o_response_code := '0';
	
	OPEN bike_cur(i_user_id, i_producer_id);
	LOOP
		FETCH bike_cur INTO bike_rec;
		EXIT WHEN bike_cur%NOTFOUND;
			
		w_index := w_index + 1;
		w_bike_arr(w_index).name := bike_rec.name;
		w_bike_arr(w_index).color := bike_rec.color;
		w_bike_arr(w_index).model := bike_rec.model;
		w_bike_arr(w_index).producer_name := bike_rec.producer_name;
		w_bike_arr(w_index).model := bike_rec.user_full_name;
		
		IF bike_rec.year IS NOT NULL THEN
			SELECT EXTRACT(YEAR FROM SYSDATE) INTO w_current_year FROM DUAL;
			w_age := w_current_year - TO_NUMBER(bike_rec.year, '9999');
			w_bike_arr(w_index).age := w_age;
		ELSE 
			w_bike_arr(w_index).age := 9999;
		END IF;
		
	END LOOP;
	CLOSE bike_cur;
	
	--COMMIT;
	
	OPEN o_bike_cursor FOR SELECT * FROM TABLE(w_bike_arr);
	
END;

   
END PKG_BIKE; 
/ 