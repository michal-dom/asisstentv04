package com.example.michal.asisstantv04.Presenters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.michal.asisstantv04.DataAccessObject.DataBase;
import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;

import com.example.michal.asisstantv04.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerListPresenter {

    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<String[]> arrayList;

    public RecyclerListPresenter(Context context) {
        this.context = context;
        this.recyclerView = ((Activity)context).findViewById(R.id.result_recycler_view);
    }

    public ChatRecyclerAdapter onRecyclerListCreate(){
//        RecyclerAdapter adapter = new RecyclerAdapter(context, getUserRequests());
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

        ChatRecyclerAdapter adapter = new ChatRecyclerAdapter(context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return adapter;

    }

    public List<Request> getUserRequests(){
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
