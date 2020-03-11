package com.br.marte.app.model;

public class StaticModel {
	private Integer os_nova;
	private Integer os_desev;
	private Integer os_pendente;	
	private Integer os_homol;
	private Integer os_gerencia;
	private Integer os_fechas;	
	private Integer jan;
	private Integer fev;
	private Integer mar;
	private Integer abr;
	private Integer mai;
	private Integer jun;
	private Integer jul;
	private Integer ago;
	private Integer set;
	private Integer out;
	private Integer nov;
	private Integer dez;
	private Integer slaDentro;
	private Integer slaFora;
	private Integer ano;
	
	public StaticModel(Integer os_nova, Integer os_desev, Integer os_pendente, Integer os_homol, Integer os_gerencia,
			Integer os_fechas, Integer jan, Integer fev, Integer mar, Integer abr, Integer mai, Integer jun,
			Integer jul, Integer ago, Integer set, Integer out, Integer nov, Integer dez ,Integer slaDentro ,Integer slaFora) {
		
		this.os_nova = os_nova;
		this.os_desev = os_desev;
		this.os_pendente = os_pendente;
		this.os_homol = os_homol;
		this.os_gerencia = os_gerencia;
		this.os_fechas = os_fechas;
		this.jan = jan;
		this.fev = fev;
		this.mar = mar;
		this.abr = abr;
		this.mai = mai;
		this.jun = jun;
		this.jul = jul;
		this.ago = ago;
		this.set = set;
		this.out = out;
		this.nov = nov;
		this.dez = dez;
		this.slaDentro = slaDentro;
		this.slaFora = slaFora;
	}
	
	
	public StaticModel(Integer jan, Integer fev, Integer mar, Integer abr, Integer mai, Integer jun,
			Integer jul, Integer ago, Integer set, Integer out, Integer nov, Integer dez ,Integer slaDentro ,Integer slaFora, Integer ano) {
		
		this.jan = jan;
		this.fev = fev;
		this.mar = mar;
		this.abr = abr;
		this.mai = mai;
		this.jun = jun;
		this.jul = jul;
		this.ago = ago;
		this.set = set;
		this.out = out;
		this.nov = nov;
		this.dez = dez;
		this.slaDentro = slaDentro;
		this.slaFora = slaFora;
		this.ano = ano;
	}
	
	public Integer getOs_nova() {
		return os_nova;
	}
	public void setOs_nova(Integer os_nova) {
		this.os_nova = os_nova;
	}
	public Integer getOs_desev() {
		return os_desev;
	}
	public void setOs_desev(Integer os_desev) {
		this.os_desev = os_desev;
	}
	public Integer getOs_pendente() {
		return os_pendente;
	}
	public void setOs_pendente(Integer os_pendente) {
		this.os_pendente = os_pendente;
	}
	public Integer getOs_homol() {
		return os_homol;
	}
	public void setOs_homol(Integer os_homol) {
		this.os_homol = os_homol;
	}
	public Integer getOs_gerencia() {
		return os_gerencia;
	}
	public void setOs_gerencia(Integer os_gerencia) {
		this.os_gerencia = os_gerencia;
	}
	public Integer getOs_fechas() {
		return os_fechas;
	}
	public void setOs_fechas(Integer os_fechas) {
		this.os_fechas = os_fechas;
	}
	public Integer getJan() {
		return jan;
	}
	public void setJan(Integer jan) {
		this.jan = jan;
	}
	public Integer getFev() {
		return fev;
	}
	public void setFev(Integer fev) {
		this.fev = fev;
	}
	public Integer getMar() {
		return mar;
	}
	public void setMar(Integer mar) {
		this.mar = mar;
	}
	public Integer getAbr() {
		return abr;
	}
	public void setAbr(Integer abr) {
		this.abr = abr;
	}
	public Integer getMai() {
		return mai;
	}
	public void setMai(Integer mai) {
		this.mai = mai;
	}
	public Integer getJun() {
		return jun;
	}
	public void setJun(Integer jun) {
		this.jun = jun;
	}
	public Integer getJul() {
		return jul;
	}
	public void setJul(Integer jul) {
		this.jul = jul;
	}
	public Integer getAgo() {
		return ago;
	}
	public void setAgo(Integer ago) {
		this.ago = ago;
	}
	public Integer getSet() {
		return set;
	}
	public void setSet(Integer set) {
		this.set = set;
	}
	public Integer getOut() {
		return out;
	}
	public void setOut(Integer out) {
		this.out = out;
	}
	public Integer getNov() {
		return nov;
	}
	public void setNov(Integer nov) {
		this.nov = nov;
	}
	public Integer getDez() {
		return dez;
	}
	public void setDez(Integer dez) {
		this.dez = dez;
	}

	public Integer getSlaDentro() {
		return slaDentro;
	}

	public void setSlaDentro(Integer slaDentro) {
		this.slaDentro = slaDentro;
	}

	public Integer getSlaFora() {
		return slaFora;
	}

	public void setSlaFora(Integer slaFora) {
		this.slaFora = slaFora;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}	
}
