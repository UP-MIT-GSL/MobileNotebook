package com.example.mobilenotebookui;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class UploadToClass extends Activity {
	
	private ProgressDialog progressDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_to_class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_upload_to_class, menu);
        return true;
    }

    public void startUpload(View view){
    	
    	//start the progress dialog
    	progressDialog = ProgressDialog.show(UploadToClass.this, "", "uploading...");
    	new Thread() {
    		
    	public void run() {

    	try{

    	sleep(10000);

    	} catch (Exception e) {

    	Log.e("tag", e.getMessage());

    	}

    	// dismiss the progress dialog

    	progressDialog.dismiss();
    	
    	backToProfile();
    	}

    	}.start();

    }
    
    
    public void backToProfile(){
    	Intent intent = new Intent(this, Logged_In.class);
    	startActivity(intent);
    }
    
    	
}
    
