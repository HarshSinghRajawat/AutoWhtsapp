package com.one.whatsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.one.whatsapp.services.SendMessageAccessibility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WhatsAppWorker extends Worker {
    public WhatsAppWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }

    @NonNull
    @Override
    public Result doWork() {

        Intent AccessibilityService=new Intent(getApplicationContext(), SendMessageAccessibility.class);
        getApplicationContext().startService(AccessibilityService);
        String text=getInputData().getString("Text");
        String num=getInputData().getString("number");

        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num+"&text="+ URLEncoder.encode(text,"UTF-8")));
            intent.setPackage("com.whatsapp");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
