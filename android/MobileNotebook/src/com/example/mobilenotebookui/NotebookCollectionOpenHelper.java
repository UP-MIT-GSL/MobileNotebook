package com.example.mobilenotebookui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotebookCollectionOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "mobilenotebook.db";
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_NOTEBOOK_COLLECTION = "notebookcollection";
	static final String SUBJECT = "subject";

	private static final String NOTEBOOK_COLLECTION_TABLE_CREATE = "CREATE TABLE "
			+ TABLE_NOTEBOOK_COLLECTION + " (" + SUBJECT + " TEXT);";


	public NotebookCollectionOpenHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(NOTEBOOK_COLLECTION_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(NotebookOpenHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTEBOOK_COLLECTION);
		onCreate(db);
	}
}
