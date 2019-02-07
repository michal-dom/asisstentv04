package com.example.michal.asisstantv04.RequestBuilders;

import java.util.HashMap;
import java.util.Map;

public class BuilderFactory implements ActionSchema {
    public static Map<String, RequestBuilder> builderMap = new HashMap<>();
    static {
        builderMap.put(TYPE_CALLING, new CallBuilder());
        builderMap.put(TYPE_LAUNCHING, new LaunchBuilder());
        builderMap.put(TYPE_SMS, new SendBuilder());
        builderMap.put(TYPE_NOTE, new NoteBuilder());
        builderMap.put(TYPE_CALENDAR, new CalendarBuilder());
        builderMap.put(TYPE_MAP, new MapBuilder());
    }

    public static RequestBuilder getBuilder(String operator) throws NullPointerException{
        RequestBuilder builder = builderMap.get(operator);
        if(builder == null){
            throw new NullPointerException("There is no such type");
        } else{
            return builder;
        }
    }

}
