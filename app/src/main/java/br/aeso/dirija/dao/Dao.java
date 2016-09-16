package br.aeso.dirija.dao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import br.aeso.dirija.entity.Alternativa;
import br.aeso.dirija.entity.Pontuacao;
import br.aeso.dirija.entity.Questao;
import br.aeso.dirija.utils.Utils;

public class Dao {

	// private static Dao dao;
	//
	// private Dao() {
	// }
	//
	// public static Dao getInstance() {
	// if (dao == null) {
	// dao = new Dao();
	// }
	// return dao;
	// }

	public static ArrayList<Questao> carregaQuiz(Context context)
			throws IOException {
		SQLiteDatabase db = Utils.loadDBFile("pk1.drj", context);
		String query = "SELECT q.id, a.id, a.idQuestao, q.imagem, q.categoria, q.texto, "
				+ "q.resposta, a.valor, a.texto, a.imagem FROM Questao q "
				+ "JOIN Alternativa a ON q.id = a.idQuestao";
		Cursor cursor = db.rawQuery(query, null);
		ArrayList<Questao> questoes = new ArrayList<Questao>();
		ArrayList<Alternativa> alternativas = new ArrayList<Alternativa>();
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				alternativas.add(new Alternativa(cursor.getInt(1), cursor
						.getInt(2), cursor.getString(8), cursor.getString(7)
						.charAt(0), (cursor.getBlob(9) != null) ? BitmapFactory
						.decodeByteArray(cursor.getBlob(9), 0,
								cursor.getBlob(9).length) : null));
				if (cursor.getString(7).equals("e")) {
					questoes.add(new Questao(cursor.getInt(0), cursor
							.getString(5), cursor.getString(4), (cursor
							.getBlob(3) != null) ? BitmapFactory
							.decodeByteArray(cursor.getBlob(3), 0,
									cursor.getBlob(3).length) : null, cursor
							.getString(6).charAt(0), alternativas));
					alternativas = new ArrayList<Alternativa>();
				}
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		return questoes;
	}

	public static ArrayList<Pontuacao> carregaPontuacao(Context context)
			throws IOException, ParseException {
		String query = "SELECT pontos, datetime(dataHora, 'localtime') AS data FROM Pontuacao ORDER BY data DESC;";
		SQLiteDatabase db = Utils.loadDBFile("pk1.drj", context);
		Cursor cursor = db.rawQuery(query, null);
		ArrayList<Pontuacao> pontos = new ArrayList<Pontuacao>();
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				pontos.add(new Pontuacao(cursor.getInt(0), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cursor.getString(1))));
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		return pontos;
	}

	public static String[] carregaPontuacaoStrings(Context context)
			throws IOException, ParseException {
		ArrayList<Pontuacao> pontuacao = carregaPontuacao(context);
		int size = pontuacao.size();
		int i = 0;
		String[] strings = new String[size];
		while (i < size) {
			strings[i] = pontuacao.get(i).toString();
			i++;
		}
		return strings;
	}

	public static void registraPontuacao(Context context, Pontuacao p)
			throws IOException {
		SQLiteDatabase db = Utils.loadDBFile("pk1.drj", context);
		ContentValues c = new ContentValues();
		c.put("pontos", p.getPontos());
		db.insertOrThrow("Pontuacao", null, c);
		db.close();
	}

	public static void apagaPontuacao(Context context) throws IOException {
		SQLiteDatabase db = Utils.loadDBFile("pk1.drj", context);
		db.delete("Pontuacao", null, null);
		db.close();
	}
}
