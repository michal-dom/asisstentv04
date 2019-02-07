package com.example.michal.asisstantv04.RequestBuilders;

import android.content.Context;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.michal.asisstantv04.AlgorithmStrategies.LevenshteinAlgorithm;
import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.CallbackRecognitionListener;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;
import com.example.michal.asisstantv04.Presenters.RecognitionCallback;
import com.example.michal.asisstantv04.Presenters.SpeechActivityPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import javax.security.auth.callback.Callback;

public class SendBuilder extends RequestBuilder implements RecognitionCallback{

    private String arg;

    private RequestReadyCallback callback;

    @Override
    public void initRequest(String utterance, Request oldRequest) {
        super.initRequest(utterance, oldRequest);

        if(oldRequest.getArguments().containsKey(ActionSchema.ARG_CONTACT)){
            List<String> words_old = new ArrayList<>();

            String contact = "";
            String core = RequestBuilder.getCore(oldRequest);

            words_old.addAll(Arrays.asList(core.split(" ")));
            List<String> words_new = new ArrayList<>();
            words_new.addAll(Arrays.asList(utterance.split(" ")));
            contact = RequestBuilder.connectString(words_new, words_old);

            Argument argument = new Argument(0, 0, contact, ActionSchema.ARG_CONTACT);
            newRequest.addArgument(argument);
        }

    }

    @Override
    public boolean isReady(String arg) {
        return false;
    }

    @Override
    public void buildRequest(Context context, View view, RequestReadyCallback callback, ChatRecyclerAdapter adapter) {
        this.callback = callback;
        for(Map.Entry me : newRequest.getArguments().entrySet()){
            if(me.getValue() == null){
                if(((String)me.getKey()).equals(ActionSchema.ARG_CONTACT)){
                    arg = ActionSchema.ADDED_ARG_CONTACT;

                    SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(context);
                    CallbackRecognitionListener listener = new CallbackRecognitionListener(context, view, this, "Do kogo mam wysłać SMS?", adapter);
                    recognizer.setRecognitionListener(listener);
                    recognizer.startListening(SpeechActivityPresenter.initRecognizerIntent());

                }
                if(((String)me.getKey()).equals(ActionSchema.ARG_BODY)){
                    arg = ActionSchema.ADDED_ARG_BODY;
                    SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(context);
                    CallbackRecognitionListener listener = new CallbackRecognitionListener(context, view, this, "Co mam wysłać?", adapter);
                    recognizer.setRecognitionListener(listener);
                    recognizer.startListening(SpeechActivityPresenter.initRecognizerIntent());

                }
            }
        }
        Log.e("test2", newRequest.toString());
    }

    @Override
    public void doCallback(String result) {
        Log.e(result, result);
        newRequest.addArgument(new Argument(0,0, result, arg));
        Log.e("test2", newRequest.toString());
        callback.getRequest(newRequest);
//        available.release();
    }

    @Override
    public Request getNewRequest() {
        return super.getNewRequest();
    }

}
