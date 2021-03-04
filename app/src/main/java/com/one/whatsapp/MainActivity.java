package com.one.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    int hr=100,min=100,sec=100,days=1;
    String time_of_exe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText text=(EditText) findViewById(R.id.getNum);
        EditText text2=(EditText) findViewById(R.id.getText);
        Button btn=(Button)findViewById(R.id.button);
        Button set=(Button)findViewById(R.id.set);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users").child("Data");




        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog picker=TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                hr=hourOfDay;
                                min=minute;
                                sec=second;
                                time_of_exe =hr+":"+min+":"+sec+":";
                            }
                        },false
                );
                picker.show(getSupportFragmentManager(),"DatePickerDialog");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Editable msg=text2.getText();
                Editable number=text.getText();
                long flexTime=CalculateFlex(hr,min,sec,days);

                String num="+91"+number.toString();
                String text=msg.toString()+".  ";
                boolean installed = isAppInstalled("com.whatsapp");

                if (installed)
                {

                    Data data=new Data.Builder()
                            .putString("number",num)
                            .putString("Text",text)
                            .putString("time", time_of_exe)
                            .build();
                    PeriodicWorkRequest sendMessage=new PeriodicWorkRequest.Builder(WhatsAppWorker.class
                            ,days,TimeUnit.DAYS,flexTime,TimeUnit.MILLISECONDS).setInputData(data)
                            .addTag("PeriodicWorker")
                            .build();

                    WorkManager.getInstance(getApplicationContext()).enqueueUniquePeriodicWork("PeriodicWorker",
                            ExistingPeriodicWorkPolicy.REPLACE,sendMessage);
                    ref.child(time_of_exe).setValue(new Task(num,text, time_of_exe,"Pending"));
                    Toast.makeText(MainActivity.this,"Work_Request_Sent",Toast.LENGTH_SHORT).show();/*
                    Intent intent = new Intent(Intent.ACTION_VIEW);

                    try {
                        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num+"&text="+ URLEncoder.encode(text,"UTF-8")));
                        intent.setPackage("com.whatsapp");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }*/

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Whatsapp is not installed!", Toast.LENGTH_SHORT).show();
                }
                //WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(FirebaseWorker.class).build();WorkManager.getInstance().enqueue(uploadWorkRequest);
            }
        });
    }
    private long CalculateFlex(int hour, int minute, int second, int periodInDays){
        Calendar call=Calendar.getInstance();
        call.set(Calendar.HOUR_OF_DAY,hour);
        call.set(Calendar.MINUTE,minute);
        call.set(Calendar.SECOND,second);

        Calendar Cal2=Calendar.getInstance();
        if(Cal2.getTimeInMillis()<call.getTimeInMillis()){
            Cal2.setTimeInMillis(Cal2.getTimeInMillis()+ TimeUnit.DAYS.toMillis(periodInDays));
        }
        long delta=(Cal2.getTimeInMillis() - call.getTimeInMillis());

        return ((delta> PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS)?delta:PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
    }
    private boolean isAppInstalled(String s) {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;

        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            is_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }
        return is_installed;
    }


}