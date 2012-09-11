package com.example.mobilenotebookui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "mobilenotebook.db";
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_USER = "user";
	static final String USER_ID = "user_id";
	static final String TOKEN = "token";

	private static final String DICTIONARY_TABLE_CREATE = "CREATE TABLE "
			+ TABLE_USER + " (" + USER_ID + " TEXT, " + TOKEN + " TEXT);";

	public UserOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DICTIONARY_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(UserOpenHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		onCreate(db);
	}
}
