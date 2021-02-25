package com.one.whatsapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestService extends Service {
    String TAG="Test";
    @Override
    public void onCreate() {
        Log.i(TAG,"TestService Created");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"Test Service Destroyed");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"TestService Inside Bind");
        return null;
    }
}
