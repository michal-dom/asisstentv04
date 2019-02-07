package com.example.michal.asisstantv04.Presenters;

import com.example.michal.asisstantv04.R;

import android.app.Notification;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.michal.asisstantv04.ActionCommands.ActionInvoker;
import com.example.michal.asisstantv04.ActionCommands.IAction;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.RequestBuilders.BuildFromJson;
import com.example.michal.asisstantv04.RequestBuilders.BuilderFactory;
import com.example.michal.asisstantv04.RequestBuilders.RequestBuilder;
import com.example.michal.asisstantv04.RequestBuilders.RequestDirector;
import com.example.michal.asisstantv04.RequestBuilders.RequestReadyCallback;
import com.example.michal.asisstantv04.ServerHandler.ConnectTask;
import com.example.michal.asisstantv04.ServerHandler.ConnectionSingleton;
import com.example.michal.asisstantv04.ServerHandler.SendMessageTask;
import com.example.michal.asisstantv04.ServerHandler.WebClient;
import com.example.michal.asisstantv04.SpeechActivity;

import java.util.ArrayList;

public class CustomRecognitionListener implements RecognitionListener, RequestReadyCallback,
        WebClient.ReceiveListener, ComponentCallbacks2 {

    private Context context;
    private SpeechRecognizer recognizer;
    private TextView textView;
    public static final String READY_TEXT = "Jestem gotowy...";
    public static final String BEGIN_TEXT = "Słucham...";
    private View  view;
    private ChatRecyclerAdapter adapter;


    public CustomRecognitionListener(Context context, View view, ChatRecyclerAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        this.view = view;
        this.textView = view.findViewById(R.id.result_text);


    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        textView.setText(READY_TEXT);
    }

    @Override
    public void onBeginningOfSpeech() {
        textView.setText(BEGIN_TEXT);
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

//        WebClient client = new WebClient(this);
//        try{
//            client.execute(words.get(0)).get();
//        }catch (Exception e){
//            Log.e("test", e.toString());
//        }


        String[] strings = {ChatRecyclerAdapter.USER, words.get(0)};
        ChatList.getInstance().addItem(strings);
        adapter.notifyItemInserted(ChatList.getInstance().getNotifier());
//
        FloatingActionButton button = view.findViewById(R.id.fab);
        ProgressBar bar = view.findViewById(R.id.progress_bar);
        SpeechActivityPresenter.showButton(button, bar);

        RequestDirector director = new RequestDirector();
        RequestBuilder builder = director.createBuilder(words.get(0));
        director = null;

        builder.buildRequest(context, view, this, adapter);
        Request request = builder.getNewRequest();
//        Log.e("test00", request.toString());


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

    @Override
    public void getRequest(Request request) {
        Log.e("callback", request.toString());
        IAction action = ActionInvoker.getAction(request);
        action.doAction(request, context, adapter);
    }

    @Override
    public void onReceive(String message) {

        Request request = BuildFromJson.buildRequest(message);

        RequestBuilder builder = BuilderFactory.getBuilder(request.action);
        builder.setNewRequest(request);
        builder.buildRequest(context, view, this, adapter);
        Request newRequest = builder.getNewRequest();
//        IAction action = ActionInvoker.getAction(request);
//        Log.e("test", newRequest.toString());
//        action.doAction(newRequest, context, adapter);

    }

    @Override
    public void onTrimMemory(int i) {

    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {

    }

    @Override
    public void onLowMemory() {

    }
}
