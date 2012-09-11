package com.example.mobilenotebookui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotebookDataSource {

	// Database fields
	private SQLiteDatabase database;
	private NotebookOpenHelper dbHelper;
	
	public NotebookDataSource(Context context) {
		dbHelper = new NotebookOpenHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void openForReading() throws SQLException {
		database = dbHelper.getReadableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Notebook2 createNotebook(String subject, int pageNumber, String path) {
		ContentValues values = new ContentValues();
		values.put(NotebookOpenHelper.SUBJECT, subject);
		values.put(NotebookOpenHelper.PAGENUMBER, pageNumber);
		values.put(NotebookOpenHelper.PATH, path);
		database.insert(NotebookOpenHelper.TABLE_NOTEBOOK, null, values);
		return getNote();
	}

	public Notebook2 getNote() {
		Cursor cursor = database.rawQuery("select * from "
				+ NotebookOpenHelper.TABLE_NOTEBOOK, null);
		Notebook2 note = new Notebook2();
		note.setSubject(cursor.getString(0));
		note.setPageNumber(cursor.getInt(1));
		note.setPath(cursor.getString(2));
		cursor.close();
		return note;
	}
}
