package com.example.michal.asisstantv04.RequestBuilders;

import android.net.Uri;
import android.util.Log;

import com.example.michal.asisstantv04.AlgorithmStrategies.AlgorithmContext;
import com.example.michal.asisstantv04.AlgorithmStrategies.IAlgorithm;
import com.example.michal.asisstantv04.DataAccessObject.DataBase;
import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RequestDirector {

    public RequestBuilder createBuilder(String utterance){
        double bestSimilarity = 0;
        Request bestRequest = null;
        AlgorithmContext context = AlgorithmContext.getInstance();
        context.setIAlgorithm(AlgorithmContext.LEVENSHTEIN_ALG);
        IAlgorithm algorithm = context.getIAlgorithm();

        for(Request r: getUserRequests()){
            String core = r.getText();
            for(Map.Entry me : r.getArguments().entrySet()){
                if(!((String)me.getKey()).contains("ADDED")){
                    core = core.replace(((Argument)me.getValue()).argContent, "");
                }
            }

            double temp = algorithm.similarity(utterance, core, 1);
            if(temp > bestSimilarity){
                bestRequest = r;
                bestSimilarity = temp;
            }
        }
        Log.e("test", bestRequest.toString());
        RequestBuilder builder = BuilderFactory.getBuilder(bestRequest.action);

        builder.initRequest(utterance, bestRequest);
        return builder;
    }


    private List<Request> getUserRequests(){
        List<Request> requests =  DataBase.mRequestDao.fetchAllRequests();
        List<Argument> args = DataBase.mArgDao.fetchAllArgs();


        for (Request request : requests){
            for (Argument arg : args){
                if(arg.requestId == request.requestId){
                    request.addArgument(arg);
                }
            }
        }
        return requests;
    }

}
