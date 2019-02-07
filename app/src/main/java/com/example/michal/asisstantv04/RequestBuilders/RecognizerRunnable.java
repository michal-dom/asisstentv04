package com.example.michal.asisstantv04.RequestBuilders;

import android.content.Context;
import android.speech.SpeechRecognizer;

import com.example.michal.asisstantv04.Presenters.CallbackRecognitionListener;
import com.example.michal.asisstantv04.Presenters.RecognitionCallback;
import com.example.michal.asisstantv04.Presenters.SpeechActivityPresenter;

public class RecognizerRunnable implements Runnable {

    private Context context;
    private CallbackRecognitionListener listener;

    public RecognizerRunnable(CallbackRecognitionListener listener, Context context){
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void run() {
        SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(context);
        recognizer.setRecognitionListener(listener);
        recognizer.startListening(SpeechActivityPresenter.initRecognizerIntent());
    }

}
