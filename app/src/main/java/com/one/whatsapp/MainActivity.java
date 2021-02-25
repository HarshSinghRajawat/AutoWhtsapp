package com.one.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.one.whatsapp.services.SendMessageAccessibility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText text=(EditText) findViewById(R.id.get);
        Button btn=(Button)findViewById(R.id.button);
        Intent intent=new Intent(MainActivity.this, SendMessageAccessibility.class);
        Toast.makeText(MainActivity.this,"ServiceStarted",Toast.LENGTH_SHORT).show();
        startService(intent);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Editable number=text.getText();


                String num="+91"+number;
                String text="This is From Java AsyncTask"+" . ";
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





                //WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(FirebaseWorker.class).build();WorkManager.getInstance().enqueue(uploadWorkRequest);
;
            }
        });

    }


}