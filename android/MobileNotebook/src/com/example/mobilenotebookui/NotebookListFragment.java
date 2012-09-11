package com.example.mobilenotebookui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

public class NotebookListFragment extends ListFragment implements
		LoaderManager.LoaderCallbacks<Cursor> {
	private static final int ACTIVITY_CREATE = 0;
	private static final int DELETE_ID = Menu.FIRST + 1;
	// private Cursor cursor;
	private SimpleCursorAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getListView().setDividerHeight(2);
		fillData();
		// registerForContextMenu(getListView());

	}

	// Create the menu based on the XML defintion
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.notebook_list, menu);
	}

	// Reaction to the menu selection
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.insert:
			createTodo();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			Uri uri = Uri.parse(NotebookContentProvider.CONTENT_URI + "/"
					+ info.id);
			getActivity().getContentResolver().delete(uri, null, null);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	private void createTodo() {
		Intent i = new Intent(getActivity(), NotebookDetailActivity.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}

	// Opens the second activity if an entry is clicked
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		/*
		 * super.onListItemClick(l, v, position, id); Intent i = new
		 * Intent(getActivity(), NotebookDetailActivity.class); Uri todoUri =
		 * Uri.parse(NotebookContentProvider.CONTENT_URI + "/" + id);
		 * i.putExtra(NotebookContentProvider.CONTENT_ITEM_TYPE, todoUri); //
		 * Activity returns an result if called with startActivityForResult
		 * startActivityForResult(i, ACTIVITY_EDIT);
		 */
		Uri notebookUri = Uri.parse(NotebookContentProvider.CONTENT_URI + "/"
				+ id);
		Toast.makeText(getActivity(), notebookUri + "", Toast.LENGTH_LONG)
				.show();
	}

	// Called with the result of the other activity
	// requestCode was the origin request code send to the activity
	// resultCode is the return code, 0 is everything is ok
	// intend can be used to get data
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
	}

	private void fillData() {

		// Fields from the database (projection)
		// Must include the _id column for the adapter to work
		String[] from = new String[] { NotebookTable.COLUMN_TITLE };
		// Fields on the UI to which we map
		int[] to = new int[] { R.id.label };

		// getLoaderManager().initLoader(0, null, this);

		getActivity().getSupportLoaderManager().initLoader(0, null, this);
		adapter = new SimpleCursorAdapter(this.getActivity(),
				R.layout.row_notebook, null, from, to, 0);

		setListAdapter(adapter);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	// Creates a new loader after the initLoader () call
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { NotebookTable.COLUMN_ID,
				NotebookTable.COLUMN_TITLE };
		CursorLoader cursorLoader = new CursorLoader(getActivity(),
				NotebookContentProvider.CONTENT_URI, projection, null, null,
				null);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// data is not available anymore, delete reference
		adapter.swapCursor(null);
	}

}