package com.one.whatsapp.services;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;

import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SendMessageAccessibility extends AccessibilityService {
    String TAG="AccessibilityService: ";
    @Override
    public void onCreate() {
        Log.i(TAG,"Service Started");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"Service Stopped ");
        stopSelf();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.i(TAG,"Service Running ");




        if(accessibilityEvent.getPackageName().toString().equals("com.whatsapp")) {

        //if(accessibilityEvent.getEventType()==AccessibilityEvent.) {



                try {
                    //Thread.sleep(200);
                    AccessibilityNodeInfoCompat rootNodeInfo = AccessibilityNodeInfoCompat.wrap(getRootInActiveWindow());
                    /*
                    Log.i(TAG, "" + rootNodeInfo.findAccessibilityNodeInfosByText("Sahil"));
                    Log.i(TAG, "" + rootNodeInfo.findAccessibilityNodeInfosByText("ACC Project"));
                    Log.i(TAG, "" + rootNodeInfo.findAccessibilityNodeInfosByText("ACC -Core Team (2nd Year)"));

                    boolean check = false;
                    //AccessibilityNodeInfo action = accessibilityEvent.getSource();
                    List<AccessibilityNodeInfoCompat> AccProject = rootNodeInfo.findAccessibilityNodeInfosByText("ACC Project");
                    performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                    performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);


                    int i = 0;
                    while (i <= 3){
                        i++;
                        performGlobalAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN.getId());
                }
                ///////////////////////////////////////////////////////////

                while (!check) {
                    //List<AccessibilityNodeInfoCompat> AccProject = rootNodeInfo.findAccessibilityNodeInfosByText("ACC -Core Team (2nd Year)");
                    List<AccessibilityNodeInfoCompat> AccProject = rootNodeInfo.findAccessibilityNodeInfosByText("ACC Project");

                    if(AccProject==null){
                        AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
                        return;}
                    AccessibilityNodeInfoCompat sendAccProject=AccProject.get(0);
                    if(sendAccProject.isVisibleToUser()){sendAccProject.performAction(AccessibilityNodeInfo.ACTION_CLICK);check=true;}
                    Log.i(TAG,"SENT"+sendAccProject.toString());

                }
                ///////////////////////////////////////////////////////////*/
                //get edit text


                List<AccessibilityNodeInfoCompat> messageNodeList= rootNodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry");

                if (messageNodeList==null||messageNodeList.isEmpty()) {
                    Log.i(TAG,"MsgField Not Found or Empty");
                    return;
                }
                Log.i(TAG,"MsgField Found!");
                AccessibilityNodeInfoCompat messageField=messageNodeList.get(0);
                if(messageField == null|| messageField.getText().length()==0||!messageField.getText().toString().endsWith(".  ")) {
                    Log.i(TAG,"Automation Not Required!");
                    return;
                }
                Log.i(TAG," Automation Required");

                //get Whatsapp send message button
                List<AccessibilityNodeInfoCompat> sendMessageNodeList = rootNodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");
                if (sendMessageNodeList == null || sendMessageNodeList.isEmpty()) {
                    Log.i(TAG, "Button Found!");
                    return;
                }
                AccessibilityNodeInfoCompat sendMessage = sendMessageNodeList.get(0);
                if (!sendMessage.isVisibleToUser()) {
                    Log.i(TAG, "No Button Found!!");
                    return;
                }
                //press button
                sendMessage.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.i(TAG, "Button Pressed:"+sendMessage);
                Thread.sleep(200);
                performGlobalAction(GLOBAL_ACTION_BACK);
                } catch (InterruptedException e) {
                    Log.i(TAG,e+"");
                }
                performGlobalAction(GLOBAL_ACTION_HOME);
            //}
        }
    }

    @Override
    public void onInterrupt()
    {
        Log.v(TAG, "Service onInterrupt");
    }

    @Override
    public void onServiceConnected()
    {
        Log.v(TAG, "Service onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.notificationTimeout = 100;
        info.feedbackType = AccessibilityEvent.TYPES_ALL_MASK;
        setServiceInfo(info);

    }


}