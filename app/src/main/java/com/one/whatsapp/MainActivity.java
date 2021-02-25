package com.one.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.one.whatsapp.services.SendMessage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static androidx.core.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //boolean installed = isAppInstalled("com.whatsapp");
        EditText text=(EditText) findViewById(R.id.get);
        Button btn=(Button)findViewById(R.id.button);
/*
        Intent intent=new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Editable number=text.getText();


                String num="+918815790199";
                String text="This is From Java AsyncTask"+"   ";
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




                Intent intent=new Intent(MainActivity.this, SendMessage.class);
                Toast.makeText(MainActivity.this,"ServiceStarted",Toast.LENGTH_SHORT).show();
                startService(intent);
                //WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(FirebaseWorker.class).build();WorkManager.getInstance().enqueue(uploadWorkRequest);
;
            }
        });

    }


}