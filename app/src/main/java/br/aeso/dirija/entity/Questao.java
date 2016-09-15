package br.aeso.dirija.entity;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Questao {
	private int id;
	private String texto, categoria;
	private Bitmap imagem;
	private char resposta;
	private ArrayList<Alternativa> alternativas;

	public Questao(int id, String texto, String categoria, Bitmap imagem,
			char resposta, ArrayList<Alternativa> alternativas) {
		this.id = id;
		this.texto = texto;
		this.categoria = categoria;
		this.imagem = imagem;
		this.resposta = resposta;
		this.alternativas = alternativas;
	}

	public int getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}

	public String getCategoria() {
		return categoria;
	}

	public Bitmap getImagem() {
		return imagem;
	}

	public char getResposta() {
		return resposta;
	}

	public ArrayList<Alternativa> getAlternativas() {
		return alternativas;
	}

}
