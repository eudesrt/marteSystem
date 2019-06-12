package com.br.marte.app.model;

public class StatusModel {
	
	private Integer evento_id;	 
	private String status;
	
	public StatusModel() {

	}	
	
	public StatusModel(Integer evento_id, String status) {
		super();
		this.evento_id = evento_id;
		this.status = status;
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
