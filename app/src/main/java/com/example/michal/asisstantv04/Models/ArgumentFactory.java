package com.example.michal.asisstantv04.Models;

import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;

import java.util.HashMap;

public class ArgumentFactory implements ActionSchema{

    private HashMap<String, Argument> CALLING_ARGS = new HashMap<>();
    private HashMap<String, Argument> SMS_ARGS = new HashMap<>();
    private HashMap<String, Argument> NOTE_ARGS = new HashMap<>();
    private HashMap<String, Argument> LAUNCHING_ARGS = new HashMap<>();
    private HashMap<String, Argument> CALENDAR_ARGS = new HashMap<>();
    private HashMap<String, Argument> MAP_ARGS = new HashMap<>();

    public HashMap<String, HashMap<String, Argument>> MAIN_MAP = new HashMap<>();

    public ArgumentFactory(){
        CALLING_ARGS.put(ARG_CONTACT, null);

        SMS_ARGS.put(ARG_CONTACT, null);
        SMS_ARGS.put(ARG_BODY, null);

        NOTE_ARGS.put(ARG_BODY, null);

        LAUNCHING_ARGS.put(ARG_APPNAME, null);

        CALENDAR_ARGS.put(ARG_DATE, null);
        CALENDAR_ARGS.put(ARG_TITLE, null);

        MAP_ARGS.put(ARG_PLACE, null);

        MAIN_MAP.put(TYPE_CALLING, CALLING_ARGS);
        MAIN_MAP.put(TYPE_SMS, SMS_ARGS);
        MAIN_MAP.put(TYPE_NOTE, NOTE_ARGS);
        MAIN_MAP.put(TYPE_LAUNCHING, LAUNCHING_ARGS);
        MAIN_MAP.put(TYPE_CALENDAR, CALENDAR_ARGS);
        MAIN_MAP.put(TYPE_MAP, MAP_ARGS);

    }

    public static String getPair(String action){
        HashMap<String, String> pairs = new HashMap<>();
        pairs.put(ADDED_ARG_CONTACT, ARG_CONTACT);
        pairs.put(ADDED_ARG_APPNAME, ARG_APPNAME);
        pairs.put(ADDED_ARG_BODY, ARG_BODY);
        pairs.put(ADDED_ARG_DATE, ARG_DATE );
        pairs.put(ADDED_ARG_PLACE, ARG_PLACE);
        pairs.put(ADDED_ARG_TITLE, ARG_TITLE );
        if(pairs.containsKey(action)){
            return pairs.get(action);
        }
        return null;
    }

}
