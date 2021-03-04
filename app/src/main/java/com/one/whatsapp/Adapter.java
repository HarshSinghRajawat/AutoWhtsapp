package com.one.whatsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Task> {

    public Adapter(@NonNull Context context, int resource, List<Task> objects) {
        super(context, resource,objects);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {

            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_layout, parent, false);
        }

        Task message = getItem(position);
        ImageView photoImageView = convertView.findViewById(R.id.imageView);
        TextView numberTextView =  convertView.findViewById(R.id.number);
        TextView statusTextView =  convertView.findViewById(R.id.status);


        photoImageView.setImageResource(R.mipmap.whatsapp_icon_round);
        numberTextView.setText(message.getNumber());
        statusTextView.setText(message.getStatus());
        View finalConvertView = convertView;/*
        convertView.setOnClickListener(view -> {
            Intent intent=new Intent(finalConvertView.getContext(),display_Data.class);
            intent.putExtra("name",message.getUser_name());

            intent.putExtra("city",message.getCity());
            intent.putExtra("age",message.getAge());
            intent.putExtra("gender",message.getGender());
            getContext().startActivity(intent);

        });
*/


        return convertView;
    }
}
