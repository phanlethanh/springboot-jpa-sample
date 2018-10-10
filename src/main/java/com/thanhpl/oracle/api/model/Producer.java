package com.thanhpl.oracle.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="m_producer", schema = "public")
public class Producer {

	@Id
	private String producerId;
	private String producerName;
	private Date createTime;
	private Date updateTime;
}
