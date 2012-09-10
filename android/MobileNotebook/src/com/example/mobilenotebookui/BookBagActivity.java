package com.example.mobilenotebookui;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BookBagActivity extends ListActivity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_bag);
		ListView listView = (ListView) findViewById(R.id.noteslist);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
		  "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		  "Linux", "OS/2" };

		// First paramenter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		  android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter); 
	}
}
