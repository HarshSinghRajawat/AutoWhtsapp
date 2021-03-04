package com.one.whatsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WifiManager wifiMan = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        int ipAddress = wifiInf.getIpAddress();
        user = String.format("%d-%d-%d-%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Users").child("Data").child(user);
        ListView list=findViewById(R.id.list);
        //Loading
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loading);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );


        List<Task> Promo_Data=new ArrayList<>();
        Adapter adapter=new Adapter(this,R.layout.custom_layout,Promo_Data);
        list.setAdapter(adapter);

        FloatingActionButton button =  findViewById(R.id.Click);
        progressDialog.dismiss();
        button.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,AddTask.class);
            i.putExtra("user",user);
            startActivity(i);
        });
        ChildEventListener mChildEventListener =new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Task data=snapshot.getValue(Task.class);
                adapter.add(data);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Task data=snapshot.getValue(Task.class);
                adapter.add(data);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Task data=snapshot.getValue(Task.class);
                adapter.add(data);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addChildEventListener(mChildEventListener);
    }
}