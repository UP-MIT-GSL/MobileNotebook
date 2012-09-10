package com.example.mobilenotebookui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDataSource {

	// Database fields
	private SQLiteDatabase database;
	private DictionaryOpenHelper dbHelper;

	public UserDataSource(Context context) {
		dbHelper = new DictionaryOpenHelper(context);
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

	public User createUser(String user_id, String token) {
		ContentValues values = new ContentValues();
		values.put(DictionaryOpenHelper.USER_ID, user_id);
		values.put(DictionaryOpenHelper.TOKEN, token);
		database.insert(DictionaryOpenHelper.TABLE_USER, null, values);
		return getUser();
	}
	
	public User getUser(){
		Cursor cursor = database.rawQuery("select * from "+DictionaryOpenHelper.TABLE_USER,null);
		User user = new User();
		user.setUserId(cursor.getString(0));
		user.setToken(cursor.getString(1));
		cursor.close();
		return user;
	}

	/*
	 * public void deleteComment(Comment comment) { long id = comment.getId();
	 * System.out.println("Comment deleted with id: " + id);
	 * database.delete(DictionaryOpenHelper.TABLE_COMMENTS,
	 * DictionaryOpenHelper.COLUMN_ID + " = " + id, null); }
	 * 
	 * public List<Comment> getAllComments() { List<Comment> comments = new
	 * ArrayList<Comment>();
	 * 
	 * Cursor cursor = database.query(DictionaryOpenHelper.TABLE_COMMENTS,
	 * allColumns, null, null, null, null, null);
	 * 
	 * cursor.moveToFirst(); while (!cursor.isAfterLast()) { Comment comment =
	 * cursorToComment(cursor); comments.add(comment); cursor.moveToNext(); } //
	 * Make sure to close the cursor cursor.close(); return comments; }
	 * 
	 * private Comment cursorToComment(Cursor cursor) { Comment comment = new
	 * Comment(); comment.setId(cursor.getLong(0));
	 * comment.setComment(cursor.getString(1)); return comment; }
	 */
}
