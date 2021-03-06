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
    
    private String descricao;
    
    private String solicitante;
	
	private Integer status;
	
	private Integer id_usuario;
	
	private String tempDescricao;	
	
	private String departamento;
	
	private String tipoSistema;
	
	private String tipoOs;

//	public OrdemServicoModel(long codigo, Integer os, String titulo, LocalDate dtEntrada, LocalDate dtHomologacao, LocalDate dtCommit,
//			LocalDate dtVencimento , Integer status, Integer id_usuario, String descricao) {
//		super();
//		this.codigo = codigo;
//		this.os = os;
//		this.titulo = titulo;
//		this.dtEntrada = dtEntrada;
//		this.dtHomologacao = dtHomologacao;
//		this.dtCommit = dtCommit;
//		this.dtVencimento = dtVencimento;
//		this.status = status;
//		this.id_usuario = id_usuario;
//		this.descricao = descricao;
//	}
	
	public OrdemServicoModel(long codigo, Integer os, String titulo, LocalDate dtEntrada, LocalDate dtHomologacao, LocalDate dtCommit,
			LocalDate dtVencimento , Integer status, Integer id_usuario,String descricao, String solicitante,String departamento, String tipoSistema, String tipoOs ) {
		this.codigo = codigo;
		this.os = os;
		this.titulo = titulo;
		this.dtEntrada = dtEntrada;
		this.dtHomologacao = dtHomologacao;
		this.dtCommit = dtCommit;
		this.dtVencimento = dtVencimento;
		this.status = status;
		this.id_usuario = id_usuario;
		this.descricao = descricao;
		this.solicitante = solicitante;
		this.departamento = departamento;
		this.tipoSistema = tipoSistema;
		this.tipoOs = tipoOs;
		
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getTempDescricao() {
		return tempDescricao;
	}

	public void setTempDescricao(String tempDescricao) {
		this.tempDescricao = tempDescricao;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getTipoSistema() {
		return tipoSistema;
	}

	public void setTipoSistema(String tipoSistema) {
		this.tipoSistema = tipoSistema;
	}

	public String getTipoOs() {
		return tipoOs;
	}

	public void setTipoOs(String tipoOs) {
		this.tipoOs = tipoOs;
	}		
	
}
