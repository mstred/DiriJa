package br.aeso.dirija.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import br.aeso.dirija.R;
import br.aeso.dirija.utils.Utils;

public class Conteudo extends Activity {

	WebView wv;
	ProgressDialog progress;

	class Client extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			progress.dismiss();
		}
	}

	public void loadWebView() {
		wv = (WebView) findViewById(R.id.webView1);
		wv.setWebViewClient(new Client());
		wv.getSettings().setAllowFileAccess(true);
		wv.getSettings().setBuiltInZoomControls(false);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setHorizontalScrollBarEnabled(false);
		wv.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);

	}

	public void loadProgressDialog() {
		progress = new ProgressDialog(this);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.onActivityCreateSetTheme(this);
		super.onCreate(savedInstanceState);
		Utils.definirContentView(this, R.layout.conteudo);
		loadProgressDialog();
		loadWebView();
		wv.loadUrl("file:///android_asset/conteudo/index.html");
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
	}
}
