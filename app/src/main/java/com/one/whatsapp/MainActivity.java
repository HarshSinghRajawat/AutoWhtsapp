package com.one.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static androidx.core.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //boolean installed = isAppInstalled("com.whatsapp");

            String num="88sudfisad";
            String text="This is From Java AsyncTask";
            if (true)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                try {
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num+"&text="+ URLEncoder.encode(text,"UTF-8")));
                    intent.setPackage("com.whatsapp");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
            else
            {
                Toast.makeText(MainActivity.this, "Whatsapp is not installed!", Toast.LENGTH_SHORT).show();
            }




        WorkRequest uploadWorkRequest =
                new OneTimeWorkRequest.Builder(FirebaseWorker.class)
                        .build();
        WorkManager.getInstance().enqueue(uploadWorkRequest);
    }


}