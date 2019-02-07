package com.example.michal.asisstantv04.Models;

import android.util.Log;

import java.util.HashMap;

public class Request {

    public int requestId;
    public String text;
    public String action;
    public HashMap<String, Argument> arguments;

    @Override
    public String toString() {

        return text + " " + action + " " + arguments.toString();


    }

    public Request(){

    }

    public Request(int requestId, String text, String action) {
        this.requestId = requestId;
        this.text = text;
        this.action = action;
        this.arguments = new ArgumentFactory().MAIN_MAP.get(action);
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
        this.arguments = new ArgumentFactory().MAIN_MAP.get(action);
    }

    public HashMap<String, Argument> getArguments() {
        return arguments;
    }

    public void addArgument(Argument argument){
//        Log.e("arg", argument)
        if(argument.argContent.equals("MISSING")){
            arguments.put(argument.type, null);
            return;
        }

        arguments.put(argument.type, argument);
        String temp = ArgumentFactory.getPair(argument.type);
        if(temp != null){
            this.arguments.remove(temp);
        }
    }





}
