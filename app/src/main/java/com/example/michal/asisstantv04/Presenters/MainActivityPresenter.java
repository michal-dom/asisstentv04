package com.example.michal.asisstantv04.Presenters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michal.asisstantv04.SpeechActivity;

public class MainActivityPresenter {

    Context context;

    public MainActivityPresenter(Context context) {
        this.context = context;
    }

    public void initViews(int id){
        TextView button = (TextView) ((AppCompatActivity) context).findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SpeechActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
