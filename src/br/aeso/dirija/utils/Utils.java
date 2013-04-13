package br.aeso.dirija.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import br.aeso.dirija.R;

public class Utils {
	
	public static int CONTENT_VIEW;
	
	public static int tema = android.R.style.Theme;
	
	public static void alert(String msg, Context context) {
    	Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
	
	@SuppressWarnings("finally")
	public static SQLiteDatabase loadDBFile(String filename, Context context)
			throws IOException {
		SQLiteDatabase db = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			File f = new File(context.getFilesDir().getAbsolutePath() + "/"
					+ filename);
			if (f.exists()) {
				db = SQLiteDatabase.openDatabase(f.getAbsolutePath(), null,
						SQLiteDatabase.OPEN_READWRITE);
			} else {
				is = context.getResources().openRawResource(R.raw.pk1);
				fos = new FileOutputStream(f);
				byte[] buffer = new byte[1024];

				while (is.read(buffer) != -1) {
					fos.write(buffer);
				}

				db = SQLiteDatabase.openDatabase(f.getAbsolutePath(), null,
						SQLiteDatabase.OPEN_READWRITE);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (is != null | fos != null) {
				is.close();
				fos.close();
				fos.flush();
			}
			return db;
		}
	}
	
	public static void changeToTheme(Activity activity, int theme)
	{
		tema = theme;
		activity.finish();
		activity.startActivity(new Intent(activity, activity.getClass()));
	}

	public static void onActivityCreateSetTheme(Activity activity)
	{
		switch (tema)
		{
		case android.R.style.Theme:
			activity.setTheme(tema);
			break;
		case android.R.style.Theme_Light:
			activity.setTheme(tema);
			break;
		}
	}

	public static void definirContentView(Activity activity, int content) {
		CONTENT_VIEW = content;
		activity.setContentView(CONTENT_VIEW);
	}
}
