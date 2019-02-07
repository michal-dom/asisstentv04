package com.example.michal.asisstantv04.ActionCommands;

import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;
import com.example.michal.asisstantv04.RequestBuilders.CalendarBuilder;
import com.example.michal.asisstantv04.RequestBuilders.CallBuilder;
import com.example.michal.asisstantv04.RequestBuilders.LaunchBuilder;
import com.example.michal.asisstantv04.RequestBuilders.MapBuilder;
import com.example.michal.asisstantv04.RequestBuilders.NoteBuilder;
import com.example.michal.asisstantv04.RequestBuilders.RequestBuilder;
import com.example.michal.asisstantv04.RequestBuilders.SendBuilder;

import java.util.HashMap;
import java.util.Map;

public class ActionInvoker implements ActionSchema {
    public static Map<String, IAction> actionMap = new HashMap<>();
    static {
        actionMap.put(TYPE_CALLING, new CallingAction());
        actionMap.put(TYPE_LAUNCHING, new LaunchingAction());
        actionMap.put(TYPE_SMS, new SendingAction());
        actionMap.put(TYPE_NOTE, new NotingAction());
        actionMap.put(TYPE_CALENDAR, new CalendarAction());
        actionMap.put(TYPE_MAP, new MapAction());
    }

    public static IAction getAction(Request request){
        return actionMap.get(request.getAction());
    }
}
