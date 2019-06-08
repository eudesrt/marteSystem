package com.br.marte.app.model;

public class GrupoStatus {
	private int os_abertas;	
	private int os_nova;
	private int os_desev;
	private int os_homol;
	private int os_fechas;
	
	public GrupoStatus(int os_abertas, int os_nova, int os_desev, int os_homol, int os_fechas) {
		super();
		this.os_abertas = os_abertas;
		this.os_nova = os_nova;
		this.os_desev = os_desev;
		this.os_homol = os_homol;
		this.os_fechas = os_fechas;
	}
	
	public int getOs_abertas() {
		return os_abertas;
	}

	public void setOs_abertas(int os_abertas) {
		this.os_abertas = os_abertas;
	}

	public int getOs_nova() {
		return os_nova;
	}

	public void setOs_nova(int os_nova) {
		this.os_nova = os_nova;
	}

	public int getOs_desev() {
		return os_desev;
	}

	public void setOs_desev(int os_desev) {
		this.os_desev = os_desev;
	}

	public int getOs_homol() {
		return os_homol;
	}

	public void setOs_homol(int os_homol) {
		this.os_homol = os_homol;
	}

	public int getOs_fechas() {
		return os_fechas;
	}

	public void setOs_fechas(int os_fechas) {
		this.os_fechas = os_fechas;
	}	
}
