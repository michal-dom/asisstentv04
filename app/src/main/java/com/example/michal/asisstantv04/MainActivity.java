package com.example.michal.asisstantv04;

import android.app.ActivityManager;
import android.content.Intent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.michal.asisstantv04.DataAccessObject.DataBase;
import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.MainActivityPresenter;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;
import com.example.michal.asisstantv04.ServerHandler.WebClient;

public class MainActivity extends AppCompatActivity {

    private SpeechRecognizer recognizer;
    private Intent recognizerIntent;
    private TextView textView;


    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityPresenter presenter = new MainActivityPresenter(MainActivity.this);
        presenter.initViews(R.id.start_text);

        DataBase mDb = new DataBase(this);

        try{
            mDb.open();
        }catch (Exception e) {
            Log.e("e", e.getMessage());
        }

        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        Log.e("onCreate", "maxMemory:" + Long.toString(maxMemory));



//        DataBase.mRequestDao.addRequest(new Request(1, "Zadzwoń do Hanna Gancarz", ActionSchema.TYPE_CALLING));
//        DataBase.mArgDao.addArgs(new Argument(1, 1, "Hanna Gancarz", ActionSchema.ARG_CONTACT));
//
//        DataBase.mRequestDao.addRequest(new Request(2, "Zadzwoń do Julia Guzik", ActionSchema.TYPE_CALLING));
//        DataBase.mArgDao.addArgs(new Argument(2, 2, "Julia Guzik", ActionSchema.ARG_CONTACT));
//
//        DataBase.mRequestDao.addRequest(new Request(3, "Uruchom Kalendarz", ActionSchema.TYPE_LAUNCHING));
//        DataBase.mArgDao.addArgs(new Argument(3, 3, "Kalendarz", ActionSchema.ARG_APPNAME));
//
//        DataBase.mRequestDao.addRequest(new Request(4, "Włącz Spotify", ActionSchema.TYPE_LAUNCHING));
//        DataBase.mArgDao.addArgs(new Argument(4, 4, "Spotify", ActionSchema.ARG_APPNAME));
//
//        DataBase.mRequestDao.addRequest(new Request(5, "Wyślij SMS", ActionSchema.TYPE_SMS));
//        DataBase.mArgDao.addArgs(new Argument(5, 5, "Dodałem nową wersję projektu na gita", ActionSchema.ADDED_ARG_BODY));
//        DataBase.mArgDao.addArgs(new Argument(6, 5, "Julia Guzik", ActionSchema.ADDED_ARG_CONTACT));
//
//        DataBase.mRequestDao.addRequest(new Request(6, "Zanotuj", ActionSchema.TYPE_NOTE));
//        DataBase.mArgDao.addArgs(new Argument(7, 6, "Treść notatki", ActionSchema.ADDED_ARG_BODY));
//
//        DataBase.mRequestDao.addRequest(new Request(7, "Zrób notatkę", ActionSchema.TYPE_NOTE));
//        DataBase.mArgDao.addArgs(new Argument(8, 7, "Treść notatki", ActionSchema.ADDED_ARG_BODY));
//
//        DataBase.mRequestDao.addRequest(new Request(8, "Pokaż drogę do restauracje", ActionSchema.TYPE_MAP));
//        DataBase.mArgDao.addArgs(new Argument(9, 8, "restauracje", ActionSchema.ARG_PLACE));
//
//        DataBase.mRequestDao.addRequest(new Request(9, "Dodaj wydarzenie w piątek", ActionSchema.TYPE_CALENDAR));
//        DataBase.mArgDao.addArgs(new Argument(10, 9, "piątek", ActionSchema.ARG_DATE));
//        DataBase.mArgDao.addArgs(new Argument(11, 9, "Spotkanie", ActionSchema.ADDED_ARG_TITLE));
//
//        DataBase.mRequestDao.addRequest(new Request(10, "Przypomnij o wizyta u lekarza", ActionSchema.TYPE_CALENDAR));
//        DataBase.mArgDao.addArgs(new Argument(12, 10, "15 styczeń", ActionSchema.ADDED_ARG_DATE));
//        DataBase.mArgDao.addArgs(new Argument(13, 10, "wizyta u lekarza", ActionSchema.ARG_TITLE));

//        DataBase.mRequestDao.addRequest(new Request(11, "Wyślij wiadomość do Julia Guzik", ActionSchema.TYPE_SMS));
//        DataBase.mArgDao.addArgs(new Argument(14, 11, "Dodałem nową wersję projektu na gita", ActionSchema.ADDED_ARG_BODY));
//        DataBase.mArgDao.addArgs(new Argument(15, 11, "Julia Guzik", ActionSchema.ADDED_ARG_CONTACT));
//

//
//        List<Request> requests =  DataBase.mRequestDao.fetchAllRequests();
//        List<Argument> args = DataBase.mArgDao.fetchAllArgs();
//
//        for (Request request : requests){
//            for (Argument arg : args){
//                if(arg.requestId == request.requestId){
//                    request.addArgument(arg);
//                }
//            }
//        }
//
//        for(Request r: requests){
////            Log.e("test", r.toString());
//        }

//        ConnectionSingleton singleton = ConnectionSingleton.getInstance();
//
//        ConnectTask task = new ConnectTask(singleton.tcpClient);
//
//        task.execute("test");






    }

}
