package com.example.michal.asisstantv04.Presenters;

import com.example.michal.asisstantv04.R;

import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CallbackRecognitionListener implements RecognitionListener {

    private Context context;
    private SpeechRecognizer recognizer;
    private TextView textView;
    public static final String READY_TEXT = "Jestem gotowy...";
    public static final String BEGIN_TEXT = "Słucham...";
    public String text = "";
    private View view;
    private RecognitionCallback callback;
    private ChatRecyclerAdapter adapter;


    public CallbackRecognitionListener(Context context, View view, RecognitionCallback callback,
                                       String text, ChatRecyclerAdapter adapter) {
        this.context = context;
        this.view = view;
        this.callback = callback;
        this.textView = view.findViewById(R.id.result_text);
        this.text = text;
        this.adapter = adapter;
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        textView.setText(READY_TEXT);
    }

    @Override
    public void onBeginningOfSpeech() {
        textView.setText(text);

        String[] strings = {ChatRecyclerAdapter.APP, text};
        ChatList.getInstance().addItem(strings);

        adapter.notifyItemInserted(ChatList.getInstance().getNotifier());

        FloatingActionButton button = view.findViewById(R.id.fab);
        ProgressBar bar = view.findViewById(R.id.progress_bar);
        SpeechActivityPresenter.hideButton(button, bar);
    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int i) {
        String errorMessage = getErrorText(i);
        Log.d("test", "FAILED " + errorMessage);
        textView.setText(errorMessage);

    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> words = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        textView.setText(words.get(0));

        String[] strings = {ChatRecyclerAdapter.USER, words.get(0)};
        ChatList.getInstance().addItem(strings);

        adapter.notifyItemInserted(ChatList.getInstance().getNotifier());

        FloatingActionButton button = view.findViewById(R.id.fab);
        ProgressBar bar = view.findViewById(R.id.progress_bar);
        SpeechActivityPresenter.showButton(button, bar);
        callback.doCallback(words.get(0));




    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }



    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "Error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}
