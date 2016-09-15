package br.aeso.dirija.activities;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import br.aeso.dirija.R;
import br.aeso.dirija.dao.Dao;
import br.aeso.dirija.entity.Pontuacao;
import br.aeso.dirija.entity.Questao;
import br.aeso.dirija.utils.Utils;

public class Prova extends Activity {

	ArrayList<Questao> quiz;
	Pontuacao pontuacao;
	TextView questao;
	RadioGroup alternativas;
	RadioButton a0, b1, c2, d3, e4;
	AlertDialog.Builder dial;
	boolean cameFromDialog = false;
	int i = 0, numDeQuestoes;
	TextView points, etc;

	protected OnClickListener ocl = new OnClickListener() {

		public void onClick(DialogInterface dialog, int which) {
			cameFromDialog = true;
			alternativas.clearCheck();
			passarQuestao();
			alternativas.invalidate();
			dialog.dismiss();
		}
	};

	protected OnCheckedChangeListener occl = new OnCheckedChangeListener() {

		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			char arg = 0;
			if (!cameFromDialog) {
				switch (arg1) {
				case R.id.prova_radio0:
					arg = 'a';
					break;
				case R.id.prova_radio1:
					arg = 'b';
					break;
				case R.id.prova_radio2:
					arg = 'c';
					break;
				case R.id.prova_radio3:
					arg = 'd';
					break;
				case R.id.prova_radio4:
					arg = 'e';
					break;
				default:
					break;
				}
				if (arg == quiz.get(i).getResposta()) {
					pontuacao.setPontos(pontuacao.getPontos() + 1);
					dial.setMessage("Correto! :)");
				} else {
					dial.setMessage("Errado! :(");
				}
				i++;
				dial.show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		numDeQuestoes = 5;
		Utils.onActivityCreateSetTheme(this);
		super.onCreate(savedInstanceState);
		Utils.definirContentView(this, R.layout.prova);
		try {
			quiz = Dao.carregaQuiz(this);
			pontuacao = new Pontuacao(0);
			questao = (TextView) findViewById(R.id.prova_textView1);
			alternativas = (RadioGroup) findViewById(R.id.prova_radioGroup1);
			alternativas.setOnCheckedChangeListener(occl);
			a0 = (RadioButton) findViewById(R.id.prova_radio0);
			b1 = (RadioButton) findViewById(R.id.prova_radio1);
			c2 = (RadioButton) findViewById(R.id.prova_radio2);
			d3 = (RadioButton) findViewById(R.id.prova_radio3);
			e4 = (RadioButton) findViewById(R.id.prova_radio4);
			dial = new Builder(this);
			dial.setNeutralButton("OK", ocl);
			passarQuestao();
		} catch (IOException e) {
			Utils.alert(e.getMessage(), this);
		}
	}

	public void click(View v) {
		switch (v.getId()) {
		case R.id.prova_button_menu:
			this.finish();
		case R.id.prova_button_pular:
			i++; passarQuestao();
		}
	}

	public void passarQuestao() {
		cameFromDialog = false;
		if (i < numDeQuestoes) {
			questao.setText(quiz.get(i).getTexto());
			a0.setText(quiz.get(i).getAlternativas().get(0).getTexto());
			b1.setText(quiz.get(i).getAlternativas().get(1).getTexto());
			c2.setText(quiz.get(i).getAlternativas().get(2).getTexto());
			d3.setText(quiz.get(i).getAlternativas().get(3).getTexto());
			e4.setText(quiz.get(i).getAlternativas().get(4).getTexto());
		} else {
			Utils.definirContentView(this, R.layout.points);
			points = (TextView) findViewById(R.id.points);
			etc = (TextView) findViewById(R.id.etc);
			points.setText(pontuacao.getPontos() + "/" + numDeQuestoes);
			if (pontuacao.getPontos() < numDeQuestoes / 3) {
				etc.setText("Too bad!");
			} else if (pontuacao.getPontos() >= numDeQuestoes / 3
					&& pontuacao.getPontos() < numDeQuestoes / 3) {
				etc.setText("Try harder...");
			} else if (pontuacao.getPontos() > numDeQuestoes / 3) {
				etc.setText("Congrats!");
			}
			try {
				Dao.registraPontuacao(this, pontuacao);
			} catch (IOException e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		}
	}
}
