package com.example.michal.asisstantv04.RequestBuilders;

import android.content.Context;
import android.view.View;

import com.example.michal.asisstantv04.AlgorithmStrategies.LevenshteinAlgorithm;
import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;

import java.util.List;
import java.util.Map;

public abstract class RequestBuilder {

    protected Request oldRequest;
    protected Request newRequest;
    protected String utterance;
    protected RequestReadyCallback callback;
    protected ChatRecyclerAdapter adapter;
    public String action;

    public void initRequest(String utterance, Request oldRequest){
        this.utterance = utterance;
        this.oldRequest = oldRequest;
        this.newRequest = new Request(0, utterance, oldRequest.getAction());
        this.action = oldRequest.getAction();

    }

    public abstract boolean isReady(String arg);
    public abstract void buildRequest(Context context, View view, RequestReadyCallback callback, ChatRecyclerAdapter adapter);

    public Request getNewRequest() {
        return newRequest;
    }

    public static String getCore(Request oldRequest){
        String core = oldRequest.getText();
        for(Map.Entry me : oldRequest.getArguments().entrySet()){
            if(!((String)me.getKey()).contains("ADDED")){
                core = core.replace(((Argument)me.getValue()).argContent, "");
            }
        }
        return core;
    }
    public static String connectString(List<String> words_new, List<String> words_old){
        String string;
        LevenshteinAlgorithm levenshtein = new LevenshteinAlgorithm();

        for(String s: words_old){
            double best = 0;
            String best_string = "";
            for(String u: words_new){
                double sim = levenshtein.similarity(s,u, 1);
                if(best < sim && sim > 0.5)
                    best_string = u;
            }
            words_new.remove(best_string);
        }


        StringBuilder sb = new StringBuilder();
        for (String s : words_new) {
            sb.append(s + " ");
        }
        string = sb.toString();
        return string;
    }

    public void setNewRequest(Request newRequest) {
        this.newRequest = newRequest;
    }
}
