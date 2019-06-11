package com.br.marte.app.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class OrdemServicoModel {
	
	
	private long codigo;
	
	private Integer os;
	
	private String titulo;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtEntrada;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtHomologacao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtCommit;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtVencimento;
	
	private Integer evento_id;
	
	private Integer id_usuario;

	public OrdemServicoModel(long codigo, Integer os, String titulo, LocalDate dtEntrada, LocalDate dtHomologacao, LocalDate dtCommit,
			LocalDate dtVencimento , Integer evento_id, Integer id_usuario) {
		super();
		this.codigo = codigo;
		this.os = os;
		this.titulo = titulo;
		this.dtEntrada = dtEntrada;
		this.dtHomologacao = dtHomologacao;
		this.dtCommit = dtCommit;
		this.dtVencimento = dtVencimento;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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


	public LocalDate getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(LocalDate dtVencimento) {
		this.dtVencimento = dtVencimento;
	}	
	

}
