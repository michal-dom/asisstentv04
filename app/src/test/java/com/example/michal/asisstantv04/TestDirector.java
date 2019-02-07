package com.example.michal.asisstantv04;

import com.example.michal.asisstantv04.AlgorithmStrategies.AlgorithmContext;
import com.example.michal.asisstantv04.AlgorithmStrategies.IAlgorithm;
import com.example.michal.asisstantv04.DataAccessObject.DataBase;
import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;
import com.example.michal.asisstantv04.RequestBuilders.BuilderFactory;
import com.example.michal.asisstantv04.RequestBuilders.RequestBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDirector {
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
        RequestBuilder builder = BuilderFactory.getBuilder(bestRequest.action);

        builder.initRequest(utterance, bestRequest);
        return builder;
    }

    private List<Request> getUserRequests(){
        List<Request> requests =  new ArrayList<>();
        List<Argument> args = new ArrayList<>();

        requests.add(new Request(1, "Zadzwoń do Hanna Gancarz", ActionSchema.TYPE_CALLING));
        args.add(new Argument(1, 1, "Hanna Gancarz", ActionSchema.ARG_CONTACT));
//
        requests.add(new Request(2, "Zadzwoń do Julia Guzik", ActionSchema.TYPE_CALLING));
        args.add(new Argument(2, 2, "Julia Guzik", ActionSchema.ARG_CONTACT));
//
        requests.add(new Request(3, "Uruchom Kalendarz", ActionSchema.TYPE_LAUNCHING));
        args.add(new Argument(3, 3, "Kalendarz", ActionSchema.ARG_APPNAME));
//
        requests.add(new Request(4, "Włącz Spotify", ActionSchema.TYPE_LAUNCHING));
        args.add(new Argument(4, 4, "Spotify", ActionSchema.ARG_APPNAME));
//
        requests.add(new Request(5, "Wyślij SMS do Julia Guzik", ActionSchema.TYPE_SMS));
        args.add(new Argument(5, 5, "Dodałem nową wersję projektu na gita", ActionSchema.ADDED_ARG_BODY));
        args.add(new Argument(6, 5, "Julia Guzik", ActionSchema.ARG_CONTACT));
//
        requests.add(new Request(6, "Zanotuj", ActionSchema.TYPE_NOTE));
        args.add(new Argument(7, 6, "Treść notatki", ActionSchema.ADDED_ARG_BODY));
//
        requests.add(new Request(7, "Zrób notatkę", ActionSchema.TYPE_NOTE));
        args.add(new Argument(8, 7, "Treść notatki", ActionSchema.ADDED_ARG_BODY));
//
        requests.add(new Request(8, "Pokaż drogę do restauracje", ActionSchema.TYPE_MAP));
        args.add(new Argument(9, 8, "restauracje", ActionSchema.ARG_PLACE));
//
        requests.add(new Request(9, "Dodaj wydarzenie w piątek", ActionSchema.TYPE_CALENDAR));
        args.add(new Argument(10, 9, "piątek", ActionSchema.ARG_DATE));
        args.add(new Argument(11, 9, "Spotkanie", ActionSchema.ADDED_ARG_TITLE));
//
        requests.add(new Request(10, "Przypomnij o wizyta u lekarza", ActionSchema.TYPE_CALENDAR));
        args.add(new Argument(12, 10, "15 styczeń", ActionSchema.ADDED_ARG_DATE));
        args.add(new Argument(13, 10, "wizyta u lekarza", ActionSchema.ARG_TITLE));

        requests.add(new Request(11, "Wyślij wiadomość do Julia Guzik", ActionSchema.TYPE_SMS));
        args.add(new Argument(14, 11, "Dodałem nową wersję projektu na gita", ActionSchema.ADDED_ARG_BODY));
        args.add(new Argument(15, 11, "Julia Guzik", ActionSchema.ARG_CONTACT));


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
