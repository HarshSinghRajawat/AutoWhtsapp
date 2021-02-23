package com.one.whatsapp;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import java.util.List;

public class WhatsappAccessibility extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if(getRootInActiveWindow()==null){
            return;
        }

        //getting root node

        AccessibilityNodeInfoCompat rootNodeInfo = AccessibilityNodeInfoCompat.wrap(getRootInActiveWindow());

        //get edit text
        List<AccessibilityNodeInfoCompat> messageNodeList= rootNodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry");

        if (messageNodeList==null||messageNodeList.isEmpty())
            return;
        AccessibilityNodeInfoCompat messageField=messageNodeList.get(0);
        if(messageField == null|| messageField.getText().length()==0||!messageField.getText().toString().endsWith("   "))
            return;


        //get Whatsapp send message button
        List<AccessibilityNodeInfoCompat> sendMessageNodeList= rootNodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");
        if (sendMessageNodeList==null||sendMessageNodeList.isEmpty())
            return;
        AccessibilityNodeInfoCompat sendMessage=sendMessageNodeList.get(0);
        if(!sendMessage.isVisibleToUser())
            return;
        //press button
        sendMessage.performAction(AccessibilityNodeInfo.ACTION_CLICK);


        try{
            Thread.sleep(2000);
            performGlobalAction(GLOBAL_ACTION_BACK);
            Thread.sleep(2000);
        }catch(InterruptedException e){

        }
        performGlobalAction(GLOBAL_ACTION_BACK);
    }

    @Override
    public void onInterrupt() {

    }
}
