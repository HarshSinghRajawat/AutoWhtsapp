package com.one.whatsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Data").child("User");
        getApplicationContext().startService(AccessibilityService);
        String text=getInputData().getString("Text");
        String num=getInputData().getString("number");
        String time_of_exe=getInputData().getString("time");

        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num+"&text="+ URLEncoder.encode(text,"UTF-8")));
            intent.setPackage("com.whatsapp");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
            ref.child(time_of_exe).setValue(new Task(num,text,time_of_exe,"Success"));
            return Result.success();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Result.failure();
        }

    }
}
