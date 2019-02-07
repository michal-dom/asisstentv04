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

public class CallBuilder extends RequestBuilder {


    @Override
    public void initRequest(String utterance, Request oldRequest) {
        super.initRequest(utterance, oldRequest);
        if(oldRequest.getArguments().containsKey(ActionSchema.ARG_CONTACT)){
            List<String> words_old = new ArrayList<>();

            String contact = "";
            String core = oldRequest.getText();
            for(Map.Entry me : oldRequest.getArguments().entrySet()){
                if(!((String)me.getKey()).contains("ADDED")){
                    core = core.replace(((Argument)me.getValue()).argContent, "");
                }
            }

            words_old.addAll(Arrays.asList(core.split(" ")));
            List<String> words_new = new ArrayList<>();
            words_new.addAll(Arrays.asList(utterance.split(" ")));
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
                sb.append(s);
                sb.append(" ");
            }
            contact = sb.toString();
            Argument argument = new Argument(0, 0, contact, ActionSchema.ARG_CONTACT);
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

    }
}
