package com.reactnativetestservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {
	private final String TAG = this.getClass().getCanonicalName();

	CrownstoneService crownstoneService = null;
	ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			Log.i(TAG, "service connected");
			crownstoneService = ((CrownstoneService.CrownstoneServiceBinder)binder).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "service disconnected");
			crownstoneService = null;
		}
	};


	/**
	 * Returns the name of the main component registered from JavaScript.
	 * This is used to schedule rendering of the component.
	 */
	@Override
	protected String getMainComponentName() {
		return "reactNativeTestService";
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");

//		// Bind to service
//		Intent intent = new Intent(this, CrownstoneService.class);
//		boolean success = this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
//		Log.i(TAG, "bind to service: " + success);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop");

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
//		unbindService(serviceConnection);
	}



}
