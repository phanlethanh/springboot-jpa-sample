package com.thanhpl.oracle.api.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.thanhpl.oracle.api.model.key.UserBikeKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="M_USER_BIKE", schema = "public")
public class UserBike {
	
	@EmbeddedId
	private UserBikeKey userBikeKey;
	
	private Date createTime;
	private Date updateTime;
}
