package br.aeso.dirija.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.aeso.dirija.R;
import br.aeso.dirija.utils.Utils;

public class Home extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Utils.onActivityCreateSetTheme(this);
		super.onCreate(savedInstanceState);
		Utils.definirContentView(this, R.layout.home);
	}

	public void click(View v) {
		switch (v.getId()) {
		case R.id.prova_button:
			startActivity(new Intent(this, Prova.class));
			break;
		case R.id.conteudo_button:
			startActivity(new Intent(this, Conteudo.class));
			break;
		case R.id.extras_button:
			startActivity(new Intent(this, Extras.class));
			break;
		case R.id.opcoes_button:
			startActivity(new Intent(this, Opcoes.class));
			break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		try {
			this.finish();
		} catch (Throwable e) {
			Utils.alert(e.getMessage(), this);
		}
	}

}