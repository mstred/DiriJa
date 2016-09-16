package br.aeso.dirija.activities;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.aeso.dirija.R;
import br.aeso.dirija.dao.Dao;
import br.aeso.dirija.utils.Utils;

public class Opcoes extends Activity {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.onActivityCreateSetTheme(this);
		super.onCreate(savedInstanceState);
		Utils.definirContentView(this, R.layout.opcoes);
		// Utils.alert(getLocalClassName(), this);
	}

	@Override
	public void onBackPressed() {
		if (Utils.CONTENT_VIEW == R.layout.historico) {
			Utils.definirContentView(this, R.layout.opcoes);
		} else {
			this.finish();
			startActivity(new Intent(this, Home.class));
		}
	}

	public void click(View v) {
		switch (v.getId()) {
//		case R.id.opcoes_button_baixar_provas:
//
//			break;
		case R.id.opcoes_button_historico:
			carregaHistorico();
			break;
		case R.id.opcoes_button_mudar_tema:
			mudaTema();
			break;
		}
	}

	public void carregaHistorico() {
		Utils.definirContentView(this, R.layout.historico);
		listView = (ListView) findViewById(R.id.historico_listView);
		try {
			listView.setAdapter(new ArrayAdapter<String>(this,
					R.layout.historico_list_item, Dao
							.carregaPontuacaoStrings(this)));
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public void mudaTema() {
		if (Utils.tema == android.R.style.Theme) {
			Utils.changeToTheme(this, android.R.style.Theme_Light);
		} else if (Utils.tema == android.R.style.Theme_Light) {
			Utils.changeToTheme(this, android.R.style.Theme);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.historico_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.hist_menu) {
			try {
				Dao.apagaPontuacao(this);
			} catch (IOException e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
			carregaHistorico();
		}
		return true;
	}
}
