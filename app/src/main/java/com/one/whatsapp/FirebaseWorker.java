package com.one.whatsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

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
