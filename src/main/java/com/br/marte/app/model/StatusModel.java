package com.br.marte.app.model;

public class StatusModel {
	
	private Long id_status;	
	private Integer evento_id;	 
	private String status;
	
	public StatusModel() {

	}	
	
	public StatusModel(Long id_status, Integer evento_id, String status) {
		super();
		this.id_status = id_status;
		this.evento_id = evento_id;
		this.status = status;
	}
	
	public StatusModel(Long id_status,  String status) {
		this.id_status = id_status;
		this.status = status;
	}

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
