CREATE OR REPLACE PACKAGE PKG_DUMMY AS 
   
	TYPE DUMMY_TYPE IS RECORD ( 
		status VARCHAR2(20),
		value_1 VARCHAR2(20),
		value_2 VARCHAR2(20)
	); 
	
	TYPE DUMMY_ARR IS TABLE OF DUMMY_TYPE INDEX BY PLS_INTEGER;
   
	PROCEDURE P_PROCESS_DUMMY(
		i_param_1 IN VARCHAR2,
		i_param_2 IN VARCHAR2, 
		o_success OUT VARCHAR2,
		o_response_code OUT VARCHAR2,
		o_cursor OUT SYS_REFCURSOR);
   
END PKG_DUMMY; 
/ 