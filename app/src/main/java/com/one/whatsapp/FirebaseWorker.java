package com.one.whatsapp;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.one.whatsapp.services.SendMessage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static androidx.core.content.ContextCompat.startActivity;

public class FirebaseWorker extends Worker {

    Context thisContext;
    public FirebaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);
        thisContext=context;
    }
    private AsyncTask mTask;

    @NonNull
    @Override
    public Result doWork() {
        mTask =new AsyncTask() {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                Log.i("FirebaseWorker: ","Inside AsyncTack");


                return null;
            }
        };
        mTask.execute();

        return Result.success();
    }

}
