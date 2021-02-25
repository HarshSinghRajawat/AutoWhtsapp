package com.one.whatsapp.services;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.loader.content.Loader;

import com.one.whatsapp.MainActivity;

import java.util.List;

public class SendMessage extends AccessibilityService {
    String TAG="Test";
    @Override
    public void onCreate() {
        Log.i(TAG,"Service Started");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"Service Stopped ");
        Log.i(TAG,"Service Stopped ");
        Log.i(TAG,"Service Stopped ");
        stopSelf();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

        Log.i(TAG,"Service Running ");


        if(accessibilityEvent.getEventType()==AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if(accessibilityEvent.getPackageName().toString().equals("com.whatsapp")) {

                AccessibilityNodeInfoCompat rootNodeInfo = AccessibilityNodeInfoCompat.wrap(getRootInActiveWindow());


                //get edit text
                List<AccessibilityNodeInfoCompat> messageNodeList= rootNodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry");

                if (messageNodeList==null||messageNodeList.isEmpty()) {
                    Log.i(TAG,"MsgField Not Found or Empty");
                    return;
                }
                Log.i(TAG,"MsgField Found!");
                AccessibilityNodeInfoCompat messageField=messageNodeList.get(0);
                if(messageField == null|| messageField.getText().length()==0||!messageField.getText().toString().endsWith("   ")) {
                    Log.i(TAG,"Automation Not Required!");
                    return;
                }
                Log.i(TAG," Automation Required");

                //get Whatsapp send message button
                List<AccessibilityNodeInfoCompat> sendMessageNodeList = null;
                AccessibilityNodeInfoCompat sendMessage = null;

                sendMessageNodeList = rootNodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");
                if (sendMessageNodeList == null || sendMessageNodeList.isEmpty()) {
                    Log.i(TAG, "Button Found!");
                    return;
                }
                sendMessage = sendMessageNodeList.get(0);
                if (!sendMessage.isVisibleToUser()) {
                    Log.i(TAG, "No Button Found!!");
                    return;
                }
                //press button
                sendMessage.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.i(TAG, "Button Pressed");

                try {
                    Thread.sleep(2000);
                    performGlobalAction(GLOBAL_ACTION_BACK);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
                performGlobalAction(GLOBAL_ACTION_BACK);
            }
        }

    }

    @Override
    public void onInterrupt()
    {
        Log.v(TAG, "***** onInterrupt");
    }

    @Override
    public void onServiceConnected()
    {
        Log.v(TAG, "***** onServiceConnected");

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.notificationTimeout = 100;
        info.feedbackType = AccessibilityEvent.TYPES_ALL_MASK;
        setServiceInfo(info);

    }


}