package com.br.marte.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_servico")
public class OrdemServico {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "codigo")
	private long codigo;

	@Column(name = "os")
	private Integer os;
	
	@Column(name="titulo")
	private String titulo;

	@Column(name = "dt_entrada")
	private LocalDate dt_entrada;

	@Column(name = "dt_homologacao")
	private LocalDate dt_homologacao;

	@Column(name = "dt_commit")
	private LocalDate dt_commit;

	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_status")
	private Status id_status;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario id_usuario;

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

	public LocalDate getDt_entrada() {
		return dt_entrada;
	}

	public void setDt_entrada(LocalDate dt_entrada) {
		this.dt_entrada = dt_entrada;
	}

	public LocalDate getDt_homologacao() {
		return dt_homologacao;
	}

	public void setDt_homologacao(LocalDate dt_homologacao) {
		this.dt_homologacao = dt_homologacao;
	}

	public LocalDate getDt_commit() {
		return dt_commit;
	}

	public void setDt_commit(LocalDate dt_commit) {
		this.dt_commit = dt_commit;
	}

	public Status getId_status() {
		return id_status;
	}

	public void setId_status(Status id_status) {
		this.id_status = id_status;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

}
