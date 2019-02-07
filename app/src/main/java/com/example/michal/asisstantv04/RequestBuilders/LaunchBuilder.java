package com.example.michal.asisstantv04.RequestBuilders;

import android.content.Context;
import android.view.View;

import com.example.michal.asisstantv04.AlgorithmStrategies.LevenshteinAlgorithm;
import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LaunchBuilder extends RequestBuilder {

    @Override
    public void initRequest(String utterance, Request oldRequest) {
        super.initRequest(utterance, oldRequest);
        if(oldRequest.getArguments().get(ActionSchema.ARG_APPNAME) != null){
            List<String> words_old = new ArrayList<>();

            String app_name = "";
            String core = RequestBuilder.getCore(oldRequest);

            words_old.addAll(Arrays.asList(core.split(" ")));
            List<String> words_new = new ArrayList<>();
            words_new.addAll(Arrays.asList(utterance.split(" ")));
            app_name = RequestBuilder.connectString(words_new, words_old);

            Argument argument = new Argument(0, 0, app_name, ActionSchema.ARG_APPNAME);
            newRequest.addArgument(argument);
        }
    }



    @Override
    public boolean isReady(String arg) {
        return false;
    }

    @Override
    public void buildRequest(Context context, View view, RequestReadyCallback callback,
                             ChatRecyclerAdapter adapter) {
        this.callback = callback;
        callback.getRequest(newRequest);

    }
}
