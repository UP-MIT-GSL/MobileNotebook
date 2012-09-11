package com.example.mobilenotebookui;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class NotebookTable {
	// Database table
	public static final String TABLE_NOTEBOOK = "notebook";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_DESCRIPTION = "description";

	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NOTEBOOK + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_TITLE
			+ " text not null, " + COLUMN_DESCRIPTION + " text not null" + ");";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(NotebookTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTEBOOK);
		onCreate(database);
	}
}
