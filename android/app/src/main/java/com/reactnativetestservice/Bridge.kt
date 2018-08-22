package com.reactnativetestservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DeviceEventManagerModule

class Bridge(val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    val TAG = this::class.java.canonicalName
    private var crownstoneService: CrownstoneService? = null
    private var serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            Log.i(TAG, "service connected")
            crownstoneService = (binder as CrownstoneService.CrownstoneServiceBinder).getService()
            crownstoneService?.subscribe(object: CrownstoneService.CrownstoneServiceListener() {
                override fun onTick() {
                    tick()
                }
            })
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.i(TAG, "service disconnected")
            crownstoneService = null
        }
    }
    init {
        Log.i(TAG, "init")
        reactContext.addLifecycleEventListener(
                object : LifecycleEventListener {
                    override fun onHostResume() {
                        Log.i(TAG, "onHostResume")
                    }
                    override fun onHostPause() {
                        Log.i(TAG, "onHostPause")
                    }
                    override fun onHostDestroy() {
                        Log.i(TAG, "onHostDestroy")
                    }
                }
        )


        // Start service
        Log.i(TAG, "startService")
        val intent = Intent(reactContext, CrownstoneService::class.java)
        if (Build.VERSION.SDK_INT >= 26) {
            reactContext.startForegroundService(intent)
        } else {
            reactContext.startService(intent)
        }


        // Bind to service
        val bindIntent = Intent(reactContext, CrownstoneService::class.java)
        val success = reactContext.bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        Log.i(TAG, "bind to service: $success")
    }

    override fun getName(): String {
        return "Bridge"
    }


    fun tick() {
        Log.i(TAG, "bridge tick")
        sendEvent(reactContext, "tick", null)
    }

    @ReactMethod
    fun tock() {
        Log.i(TAG, "bridge tock")
    }

    fun sendEvent(reactContext: ReactContext, eventName: String, params: WritableMap?) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java).emit(eventName, params)
    }

}