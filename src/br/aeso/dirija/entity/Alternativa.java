package br.aeso.dirija.entity;

import android.graphics.Bitmap;

public class Alternativa {
	private int id, idQuestao;
	private String texto;
	private char valor;
	private Bitmap imagem;
	
	public Alternativa(int id, int idQuestao, String texto, char valor, Bitmap imagem) {
		this.id = id;
		this.idQuestao = idQuestao;
		this.texto = texto;
		this.valor = valor;
		this.imagem = imagem;
	}

	public int getId() {
		return id;
	}

	public int getIdQuestao() {
		return idQuestao;
	}

	public String getTexto() {
		return texto;
	}

	public Bitmap getImagem() {
		return imagem;
	}
	
	public char getValor() {
		return valor;
	}
}