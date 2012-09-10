package com.example.mobilenotebookui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NotebookCollectionDataSource {

	// Database fields
	private SQLiteDatabase database;
	private NotebookCollectionOpenHelper dbHelper;

	public NotebookCollectionDataSource(Context context) {
		dbHelper = new NotebookCollectionOpenHelper(context);
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

	public NotebookCollection createNotebookCollection(String subject) {
		ContentValues values = new ContentValues();
		values.put(NotebookCollectionOpenHelper.SUBJECT, subject);
		database.insert(NotebookCollectionOpenHelper.TABLE_NOTEBOOK_COLLECTION,
				null, values);
		return getNotebookCollection();
	}

	public NotebookCollection getNotebookCollection() {
		Cursor cursor = database.rawQuery("select * from "
				+ NotebookCollectionOpenHelper.TABLE_NOTEBOOK_COLLECTION, null);
		NotebookCollection notebook = new NotebookCollection();
		notebook.setSubject(cursor.getString(0));
		cursor.close();
		return notebook;
	}
}
