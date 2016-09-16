package br.aeso.dirija.entity;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Pontuacao {
	private int id, pontos;
	private Date data;

	public Pontuacao(int id, int pontos, Date data) {
		this.id = id;
		this.pontos = pontos;
		this.data = data;
	}

	public Pontuacao(int pontos, Date data) {
		this.pontos = pontos;
		this.data = data;
	}

	public Pontuacao(int pontos) {
		this.pontos = pontos;
	}

	public int getId() {
		return id;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Data: " + SimpleDateFormat.getDateTimeInstance().format(data)
				+ " || Pontos: " + this.pontos;
	}
}
