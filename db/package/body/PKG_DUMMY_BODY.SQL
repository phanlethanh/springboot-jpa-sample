CREATE OR REPLACE PACKAGE BODY PKG_DUMMY AS
   
PROCEDURE P_PROCESS_DUMMY(
		i_param_1 IN VARCHAR2,
		i_param_2 IN VARCHAR2, 
		o_success OUT VARCHAR2,
		o_response_code OUT VARCHAR2,
		o_cursor OUT SYS_REFCURSOR) IS
	
	w_index PLS_INTEGER := 0;
	w_arr DUMMY_ARR;
	w_success VARCHAR2(20);
	w_response_code VARCHAR2(20);
	w_value_code VARCHAR2(50);

	CURSOR new_cur(param IN VARCHAR2) IS
		SELECT * FROM dummy WHERE id = param;
	

	new_rec new_cur%ROWTYPE;

BEGIN
	
	o_success := 'true';
	o_response_code := '000';
	
	OPEN new_cur(i_param_1);
	LOOP
		FETCH new_cur INTO new_rec;
		EXIT WHEN new_cur%NOTFOUND;
		
		o_success := 'false';
		o_response_code := '100';
	
		-- Process
			
		w_index := w_index + 1;
		w_arr(w_index).status := 'Ok';
		w_arr(w_index).value_1 := new_rec.dummy_field;
		w_arr(w_index).value_2 := w_value_code;
		
	END LOOP;
	CLOSE new_cur;
	
	COMMIT;
	
	OPEN o_cursor FOR SELECT * FROM TABLE(w_arr);
	
END;

   
END PKG_DUMMY; 
/ 