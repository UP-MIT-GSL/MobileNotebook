package com.example.mobilenotebookui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class NotebookListActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Create new fragment and transaction
		Fragment fragment = new NotebookListFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.layout.activity_notebook_list, fragment);
		// Commit the transaction
		transaction.commit();
	}
}
