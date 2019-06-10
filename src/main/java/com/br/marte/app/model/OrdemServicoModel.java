package com.br.marte.app.model;

import java.time.LocalDate;

public class OrdemServicoModel {
	
	
	private long codigo;
	
	private Integer os;
	
	private String titulo;
	
	private LocalDate dtEntrada;

	private LocalDate dtHomologacao;

	private LocalDate dtCommit;
	
	private Integer evento_id;
	
	private Integer id_usuario;

	public OrdemServicoModel(long codigo, Integer os, String titulo, LocalDate dtEntrada, LocalDate dtHomologacao, LocalDate dtCommit,
			Integer evento_id, Integer id_usuario) {
		super();
		this.codigo = codigo;
		this.os = os;
		this.titulo = titulo;
		this.dtEntrada = dtEntrada;
		this.dtHomologacao = dtHomologacao;
		this.dtCommit = dtCommit;
		this.evento_id = evento_id;
		this.id_usuario = id_usuario;
	}
	
	public OrdemServicoModel() {
		
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Integer getOs() {
		return os;
	}

	public void setOs(Integer os) {
		this.os = os;
	}

	public LocalDate getDtEntrada() {
		return dtEntrada;
	}

	public void setDtEntrada(LocalDate dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

	public LocalDate getDtHomologacao() {
		return dtHomologacao;
	}

	public void setDtHomologacao(LocalDate dtHomologacao) {
		this.dtHomologacao = dtHomologacao;
	}

	public LocalDate getDtCommit() {
		return dtCommit;
	}

	public void setDtCommit(LocalDate dtCommit) {
		this.dtCommit = dtCommit;
	}

	public Integer getEvento_id() {
		return evento_id;
	}

	public void setEvento_id(Integer evento_id) {
		this.evento_id = evento_id;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}		

}
