CREATE OR REPLACE PACKAGE BODY  PKG_ENTITY AS

FUNCTION F_SAVE_BIKE(
		i_name IN VARCHAR2,
		i_color IN VARCHAR2,
		i_model IN VARCHAR2,
		i_user_id IN VARCHAR2) RETURN VARCHAR2
	AS
		o_bike_id VARCHAR2 := 0;
	BEGIN
		SELECT bike_seq.nextval 
		INTO o_bike_id 
		FROM DUAL;

		INSERT INTO M_BIKE(
			bike_id,
			name,
			color,
			model,
			create_time
		)
		VALUES(
			o_bike_id,
			i_name,
			i_color,
			i_model,
			SYSDATE
		);
		COMMIT;
		RETURN o_bike_id;
	END F_SAVE_BIKE;

END PKG_ENTITY;