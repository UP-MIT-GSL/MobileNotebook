package com.example.mobilenotebookui;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraDemo extends Activity {
	private static final String TAG = "CameraDemo";
	Camera camera;
	Preview preview;
	Button buttonClick;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_demo);

		preview = new Preview(this);
		((FrameLayout) findViewById(R.id.preview)).addView(preview);

		buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				preview.camera.takePicture(shutterCallback, rawCallback,
						jpegCallback);
				
				AlertDialog alertDialog = new AlertDialog.Builder(CameraDemo.this).create();
				alertDialog.setTitle("Upload");
				alertDialog.setMessage("Upload in one of your classes?");
				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE , "OK", new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int which) {
				 
				       //here you can add functions
				    	invokeClassSelector();
				    	
				 
				    } });
				
				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						onCreate(null);
					}
				});
				
				alertDialog.show();
			
			}
		});

		Log.d(TAG, "onCreate'd");
	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	/** Handles data for raw picture */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");
		}
	};

	/** Handles data for jpeg picture */
	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			FileOutputStream outStream = null;
			
			/*
              ContentValues values = new ContentValues();
		      values.put(Images.Media.TITLE, "title");
		      values.put(Images.Media.BUCKET_ID, "test");
		      values.put(Images.Media.DESCRIPTION, "test Image taken");
		      values.put(Images.Media.MIME_TYPE, "image/jpeg");
		      Uri uri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
		      OutputStream outstream;
		      
		              try {
                    outstream = getContentResolver().openOutputStream(uri);
                    outStream.write(data);
      				outStream.close();
      				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
              } catch (FileNotFoundException e) {
                      Log.d(TAG, "FNFE");
              }catch (IOException e){
            	  		Log.d(TAG, "IOE");
              }*/
		
		
			
			
			try {
				// write to local sandbox file system
				// outStream =
				// CameraDemo.this.openFileOutput(String.format("%d.jpg",
				// System.currentTimeMillis()), 0);
				// Or write to sdcard
				outStream = new FileOutputStream(String.format(
						"/sdcard/%d.jpg", System.currentTimeMillis()));
				outStream.write(data);
				outStream.close();
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
			} catch (FileNotFoundException e) {
				Log.d(TAG,"FNFE");
				e.printStackTrace();
			} catch (IOException e) {
				Log.d(TAG, "IOE");
				e.printStackTrace();
			} finally {
			}
			
			Log.d(TAG, "onPictureTaken - jpeg");
		}
	};
	
	public void invokeClassSelector(){
		Intent intent = new Intent(this, UploadToClass.class);
		startActivity(intent);
		
	}

}