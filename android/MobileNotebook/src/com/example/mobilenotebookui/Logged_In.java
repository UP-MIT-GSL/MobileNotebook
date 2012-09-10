package com.example.mobilenotebookui;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class Logged_In extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged__in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_logged__in, menu);
        return true;
    }
    
    public void startCamera(View view){
    	//Intent intent = new Intent(this, CameraDemo.class);
    	Intent intent = new Intent(this, CameraActivity.class);
    	
    	startActivity(intent);
    }

    
}
