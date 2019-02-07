package com.example.michal.asisstantv04.Presenters;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.michal.asisstantv04.SpeechActivity;

import java.io.BufferedInputStream;
import java.util.Locale;

public class SpeechActivityPresenter  {

    Context context;

    public SpeechActivityPresenter(Context context) {
        this.context = context;
    }

    public void initViews(final int btnId, final int pbId, final SpeechRecognizer recognizer) {
        final FloatingActionButton button = (FloatingActionButton) ((AppCompatActivity) context).findViewById(btnId);
        final ProgressBar progressBar = (ProgressBar) ((AppCompatActivity) context).findViewById(pbId);
        progressBar.getIndeterminateDrawable().setColorFilter(0xd1d1d100, android.graphics.PorterDuff.Mode.MULTIPLY);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recognizer.startListening(initRecognizerIntent());
                hideButton(button, progressBar);
            }
        });



    }

    public static Intent initRecognizerIntent(){
        Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_WEB_SEARCH_ONLY, "false");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, "6000");

        return recognizerIntent;

    }

    public static void showButton(FloatingActionButton button, ProgressBar progressBar){
        button.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public static void hideButton(FloatingActionButton button, ProgressBar progressBar){

        button.setVisibility(View.INVISIBLE);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }


}
