package com.example.michal.asisstantv04.ActionCommands;

import android.content.Context;

import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;

public interface IAction {
    public void doAction(Request request, Context context, ChatRecyclerAdapter adapter);
}
