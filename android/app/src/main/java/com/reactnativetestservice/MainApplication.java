package com.reactnativetestservice;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {
	private final String TAG = this.getClass().getCanonicalName();

	private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
		@Override
		public boolean getUseDeveloperSupport() {
			return BuildConfig.DEBUG;
		}

		@Override
		protected List<ReactPackage> getPackages() {
			return Arrays.<ReactPackage>asList(
					new MainReactPackage(),
					new BridgePacket()
			);
		}

		@Override
		protected String getJSMainModuleName() {
			return "index";
		}
	};

	@Override
	public ReactNativeHost getReactNativeHost() {
		return mReactNativeHost;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate");
		SoLoader.init(this, /* native exopackage */ false);

//		// Start service
//		Log.i(TAG, "startService");
//		Intent intent = new Intent(this, CrownstoneService.class);
//		if (Build.VERSION.SDK_INT >= 26) {
//			startForegroundService(intent);
//		}
//		else {
//			startService(intent);
//		}

//		// Bind to service
//		Intent bindIntent = new Intent(this, CrownstoneService.class);
//		boolean success = this.bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
//		Log.i(TAG, "bind to service: " + success);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.i(TAG, "onTerminate");
	}

//	CrownstoneService crownstoneService = null;
//	ServiceConnection serviceConnection = new ServiceConnection() {
//		@Override
//		public void onServiceConnected(ComponentName name, IBinder binder) {
//			Log.i(TAG, "service connected");
//			crownstoneService = ((CrownstoneService.CrownstoneServiceBinder)binder).getService();
//		}
//
//		@Override
//		public void onServiceDisconnected(ComponentName name) {
//			Log.i(TAG, "service disconnected");
//			crownstoneService = null;
//		}
//	};
}
