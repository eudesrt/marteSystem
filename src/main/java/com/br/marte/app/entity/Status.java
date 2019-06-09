package com.br.marte.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_status")
public class Status {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id_status")
	private Long id_status;
	
	@Column(name = "evento_id")
	private Integer evento_id;
	 
	@Column(name = "status")
	private String status;

	public Long getId_status() {
		return id_status;
	}

	public void setId_status(Long id_status) {
		this.id_status = id_status;
	}

	public Integer getEvento_id() {
		return evento_id;
	}

	public void setEvento_id(Integer evento_id) {
		this.evento_id = evento_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
