package com.example.michal.asisstantv04.RequestBuilders;

public interface ActionSchema {

    public static final String TYPE_CALLING = "CALLING";
    public static final String TYPE_SMS = "SMS";
    public static final String TYPE_LAUNCHING = "LAUNCHING";
    public static final String TYPE_NOTE = "NOTE";
    public static final String TYPE_CALENDAR = "CALENDAR";
    public static final String TYPE_MAP = "MAP";

    public static final String ARG_CONTACT = "ARG_CONTACT";
    public static final String ARG_BODY = "ARG_BODY";
    public static final String ARG_APPNAME = "ARG_APPNAME";
    public static final String ARG_DATE = "ARG_DATE";
    public static final String ARG_TITLE = "ARG_TITLE";
    public static final String ARG_PLACE = "ARG_PLACE";

    public static final String ADDED_ARG_CONTACT = "ADDED_ARG_CONTACT";
    public static final String ADDED_ARG_BODY = "ADDED_ARG_BODY";
    public static final String ADDED_ARG_APPNAME = "ADDED_ARG_APPNAME";
    public static final String ADDED_ARG_DATE = "ADDED_ARG_DATE";
    public static final String ADDED_ARG_TITLE = "ADDED_ARG_TITLE";
    public static final String ADDED_ARG_PLACE = "ADDED_ARG_PLACE";

    public static final String ARG_MISSING = "MISSING";
}
