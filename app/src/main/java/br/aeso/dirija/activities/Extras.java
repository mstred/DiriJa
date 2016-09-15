package br.aeso.dirija.activities;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.aeso.dirija.R;
import br.aeso.dirija.utils.Utils;

public class Extras extends Activity {

	Builder dial;

	ListView lista;

	static int CONTENT_VIEW;

	protected OnItemClickListener oicl = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			String url = "";
			switch (position) {
			case 0:
				url = "http://www.preferenciaavida.com.br/tag/educacao-de-transito/";
				break;
			case 1:
				url = "http://tudosobreseguranca.com.br/portal/index.php?option=com_content&task=view&id=303&Itemid=160";
				break;
			case 2:
				url = "http://www.detran.se.gov.br/educ_glossario.asp";
				break;
			case 3:
				url = "http://educacaotransitoportalprofessor.wordpress.com/";
				break;
			case 4:
				url = "http://www.osvigaristas.com.br/animacoes/situacoes/educacao-no-transito-44.html";
				break;
			case 5:
				url = "http://www.youtube.com/watch?v=8cAXul6uzCo";
				break;
			case 6:
				url = "http://jornalmaisnoticias.com.br/mobilizacao-pela-educacao-no-transito-comeca-a-dar-sinais-positivos";
				break;
			case 7:
				url = "http://www.youtube.com/watch?&v=ST7Y_YjVEtk";
				break;
			case 8:
				url = "http://www.youtube.com/watch?v=YtRYw-bzYzM";
				break;
			}
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			// (String) ((TextView) arg1).getText())
		}
	};

	String[] titulos = {
			"Educação De Trânsito | Dê Preferência À Vida",
			"Tudo sobre Segurança - Cartilhas de Segurança no Trânsito",
			"Detran - Glossário",
			"Educação no Trânsito",
			"Animação - Educação no Trãnsito",
			"Pegadinha - O Manobrista Bebado - YouTube",
			"Mobilização pela Educação no Trânsito começa a dar sinais positivos",
			"Governo de Pernambuco - Motos EPI - YouTube",
			"Governo de Pernambuco - Motos Imprudência - YouTube" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.onActivityCreateSetTheme(this);
		super.onCreate(savedInstanceState);
		Utils.definirContentView(this, R.layout.extras);
		// Utils.alert(getLocalClassName(), this);
	}

	@Override
	public void onBackPressed() {
		if (Utils.CONTENT_VIEW == R.layout.links) {
			Utils.definirContentView(this, R.layout.extras);
		} else {
			this.finish();
		}
	}

	public void click(View v) {
		switch (v.getId()) {
		case R.id.extras_button_links:
			carregaListView();
			break;
		case R.id.extras_button_sobre:
			carregaDialog();
			break;
		}
	}

	public void carregaListView() {
		Utils.definirContentView(this, R.layout.links);
		lista = (ListView) findViewById(R.id.links_listView);
		lista.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, titulos));
		lista.setOnItemClickListener(oicl);
	}

	public void carregaDialog() {
		dial = new Builder(this);
		TextView text = new TextView(this);
		text.setPadding(5, 5, 5, 5);
		text.setTextColor(Color.WHITE);
		text.setTextSize(26);
		text.setText(Html
				.fromHtml("Aplicação desenvolvida por <a href=\"http://linkedin.com/pub/edson-samuel-jr/14/51b/47b\">Edson Samuel</a> e <a href=\"http://linkedin.com/pub/danielle-barbosa-de-lima/3a/699/76b\">Danielle Barbosa</a> para a disciplina de Desenvolvimento de Software para Dispositivos Móveis"));
		text.setMovementMethod(LinkMovementMethod.getInstance());
		dial.setView(text);
//		dial.setNeutralButton(" ", new OnClickListener() {
//
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
		dial.show();
	}

}
