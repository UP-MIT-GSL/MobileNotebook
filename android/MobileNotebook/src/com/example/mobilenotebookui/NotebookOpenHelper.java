package com.example.mobilenotebookui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotebookOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "mobilenotebook.db";
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_NOTEBOOK = "notebook";
	static final String SUBJECT = "subject";
	static final String PAGENUMBER = "pageno";
	static final String PATH = "path";

	private static final String NOTEBOOK_TABLE_CREATE = "CREATE TABLE "
			+ TABLE_NOTEBOOK + " ("+ SUBJECT+ "TEXT" + PAGENUMBER + " INTEGER, " + PATH + " TEXT);";

	public NotebookOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(NOTEBOOK_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(NotebookOpenHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTEBOOK);
		onCreate(db);
	}
}
