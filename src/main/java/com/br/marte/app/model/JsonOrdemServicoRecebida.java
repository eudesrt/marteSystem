package com.br.marte.app.model;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonOrdemServicoRecebida {

	public Integer id;
	public String titulo;
	public String solicitante;
	public String emailSolicitante;
	public String departamentoSolicitante;
	public String prioridade;
	public String dataCriacao;
	public String tipoOs;
	public String tipoSistema;
	public String dataUltimoEvento;
	public String dataVencimento;
	public Integer horasPrevistas;
	public Object horasGastas;
	public Integer diasVencimento;
	public String comDesenvolvedor;
	public Evento evento;
//	public DepartamentoAbertura departamentoAbertura;
//	public List<Status> status = null;
//	public List<Arquivo> arquivos = null;

	public static JsonOrdemServicoRecebida create(String json) {
		try {
			return new Gson().fromJson(json, JsonOrdemServicoRecebida.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static List<JsonOrdemServicoRecebida> creates(String json) {

		Type collectionType = new TypeToken<List<JsonOrdemServicoRecebida>>() {
		}.getType();

		return new Gson().fromJson(json, collectionType);
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getEmailSolicitante() {
		return emailSolicitante;
	}

	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}

	public String getDepartamentoSolicitante() {
		return departamentoSolicitante;
	}

	public void setDepartamentoSolicitante(String departamentoSolicitante) {
		this.departamentoSolicitante = departamentoSolicitante;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getTipoOs() {
		return tipoOs;
	}

	public void setTipoOs(String tipoOs) {
		this.tipoOs = tipoOs;
	}

	public String getTipoSistema() {
		return tipoSistema;
	}

	public void setTipoSistema(String tipoSistema) {
		this.tipoSistema = tipoSistema;
	}

	public String getDataUltimoEvento() {
		return dataUltimoEvento;
	}

	public void setDataUltimoEvento(String dataUltimoEvento) {
		this.dataUltimoEvento = dataUltimoEvento;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getHorasPrevistas() {
		return horasPrevistas;
	}

	public void setHorasPrevistas(Integer horasPrevistas) {
		this.horasPrevistas = horasPrevistas;
	}

	public Object getHorasGastas() {
		return horasGastas;
	}

	public void setHorasGastas(Object horasGastas) {
		this.horasGastas = horasGastas;
	}

	public Integer getDiasVencimento() {
		return diasVencimento;
	}

	public void setDiasVencimento(Integer diasVencimento) {
		this.diasVencimento = diasVencimento;
	}

	public String getComDesenvolvedor() {
		return comDesenvolvedor;
	}

	public void setComDesenvolvedor(String comDesenvolvedor) {
		this.comDesenvolvedor = comDesenvolvedor;
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public static class Evento {
		public Integer id;
		public String nome;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}		
	}
	
	public static class JsonOrdemServicoConsulta {
		public List<JsonOrdemServicoRecebida> content = null;
		
		public static JsonOrdemServicoConsulta create(String json) {
			try {
				return new Gson().fromJson(json, JsonOrdemServicoConsulta.class);
			} catch (Exception e) {
				return null;
			}
		}		
		
		public static List<JsonOrdemServicoConsulta> creates(String json) {

			Type collectionType = new TypeToken<List<JsonOrdemServicoConsulta>>() {
			}.getType();

			return new Gson().fromJson(json, collectionType);
		}

		public List<JsonOrdemServicoRecebida> getContent() {
			return content;
		}

		public void setContent(List<JsonOrdemServicoRecebida> content) {
			this.content = content;
		}
		
		public String toJSON() {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			return gson.toJson(this);
		}
	}
}
