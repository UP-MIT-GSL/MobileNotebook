package com.example.mobilenotebookui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;

public class LauncherActivity extends Activity {

	private final String ACCOUNT_FILE = "mobilenotebook_registered_device";
	private GoogleAccountManager accountManager;
	private final int DIALOG_ACCOUNTS = 1;
	private String SCOPE = "https://www.googleapis.com/auth/drive.file";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		File registration = this.getFileStreamPath(ACCOUNT_FILE);
		if (registration.exists()) {
		} else {
			accountManager = new GoogleAccountManager(this);
			showDialog(DIALOG_ACCOUNTS);
			Intent dash = new Intent(this, MainActivity.class);
			finish();
			startActivity(dash);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_ACCOUNTS:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Select a Google account");
			final Account[] accounts = accountManager.getAccounts();
			final int size = accounts.length;
			String[] names = new String[size];
			for (int i = 0; i < size; i++) {
				names[i] = accounts[i].name;
			}
			builder.setItems(names, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// Stuff to do when the account is selected by the user
					gotAccount(accounts[which]);
				}
			});
			return builder.create();
		}
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void gotAccount(Account account) {
		Bundle options = new Bundle();
		accountManager.getAccountManager().getAuthToken(account, SCOPE,
				options, this, new OnTokenAcquired(), null);

	}

	private class OnTokenAcquired implements AccountManagerCallback<Bundle> {

		@Override
		public void run(AccountManagerFuture<Bundle> future) {
			// TODO Auto-generated method stub
			try {
				FileOutputStream accountInfo = openFileOutput(ACCOUNT_FILE,
						Context.MODE_PRIVATE);
				Bundle bundle = future.getResult();
				accountInfo.write("{".getBytes());
				accountInfo.write("\"accountName\":".getBytes());
				accountInfo.write("\"".getBytes());
				accountInfo.write(bundle.getString(AccountManager.KEY_ACCOUNT_NAME).getBytes());
				accountInfo.write("\"".getBytes());
				accountInfo.write(",".getBytes());
				accountInfo.write("\"authToken\":".getBytes());
				accountInfo.write("\"".getBytes());
				accountInfo.write(bundle
						.getString(AccountManager.KEY_AUTHTOKEN).getBytes());
				accountInfo.write("\"".getBytes());
				accountInfo.write("}".getBytes());
				accountInfo.close();
			} catch (OperationCanceledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AuthenticatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}