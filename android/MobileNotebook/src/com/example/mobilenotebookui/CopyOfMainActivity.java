




/*package com.example.mobilenotebookui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;

public class MainActivity extends FragmentActivity {

	private final String ACCOUNT_FILE = "mobilenotebook_registered_device";
	private GoogleAccountManager accountManager;
	private String SCOPE = "https://www.googleapis.com/auth/drive.file";
	private UserDataSource userData;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		userData = new UserDataSource(this);
		userData.openForReading();
		File registration = this.getFileStreamPath(ACCOUNT_FILE);
		if (registration.exists()) {
		} else {
			accountManager = new GoogleAccountManager(this);
			showDialog();
			Intent dash = new Intent(this, MainActivity.class);
			finish();
			startActivity(dash);
		}
	}

	void showDialog() {
		DialogFragment chooser = new AccountChooserDialog();
		chooser.show(getSupportFragmentManager(), "dialog");
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
				accountInfo.write(bundle.getString(
						AccountManager.KEY_ACCOUNT_NAME).getBytes());
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	public void signUpPage(View view) {
		Intent intent = new Intent(this, SignUpActivity.class);
		startActivity(intent);
	}

	public void profilePage(View view) {
		Intent intent = new Intent(this, Logged_In.class);
		startActivity(intent);
	}

	private class AccountChooserDialog extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MainActivity.this);
			builder.setTitle("Select a Google Account");
			final Account[] accounts = accountManager.getAccounts();
			final int size = accounts.length;
			final String[] names = new String[size];
			for (int i = 0; i < size; i++) {
				names[i] = accounts[i].name;
			}
			builder.setItems(names, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// Stuff to do when the account is selected by the user
					Toast.makeText(MainActivity.this, names[which],
							Toast.LENGTH_SHORT).show();
					gotAccount(accounts[which]);
				}
			});

			return builder.create();
		}
	}

}
*/