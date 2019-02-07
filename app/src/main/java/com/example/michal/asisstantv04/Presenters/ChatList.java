package com.example.michal.asisstantv04.Presenters;

import java.util.ArrayList;

public class ChatList {
    private static final ChatList ourInstance = new ChatList();
    public static final String USER = "USER_BALOON";
    public static final String APP = "APP_BALOON";
    private ArrayList<String[]> chat = new ArrayList<>();

    public static ChatList getInstance() {
        return ourInstance;
    }

    private ChatList() {
        String[] strings = {ChatRecyclerAdapter.APP, "Co mam zrobić?"};
        String[] strings1 = {ChatRecyclerAdapter.USER, "wyślij SMS"};
        String[] strings2 = {ChatRecyclerAdapter.APP, "Do kogo mam wysłać SMS?"};
        String[] strings3 = {ChatRecyclerAdapter.USER, "Jan Kowalski"};
        String[] strings4 = {ChatRecyclerAdapter.APP, "Co mam wysłać?"};
        String[] strings5 = {ChatRecyclerAdapter.USER, "treść wiadomości"};
        String[] strings6 = {ChatRecyclerAdapter.APP, "Wysyłam wiadomość do Jan Kowalski"};
        String[] strings7 = {ChatRecyclerAdapter.APP, "Co mam jeszcze zrobić?"};
        chat.add(strings);
        chat.add(strings1);
        chat.add(strings2);
        chat.add(strings3);
        chat.add(strings4);
        chat.add(strings5);
        chat.add(strings6);
        chat.add(strings7);
    }

    public ArrayList<String[]> getChat() {
        return chat;
    }

    public void addItem(String[] strings){
        chat.add(strings);
    }

    public int getNotifier(){
        return chat.size()-1;
    }

    public void resetList(){
        String[] strings = {ChatRecyclerAdapter.APP, "Co mam jeszcze zrobić?"};
        chat.add(strings);
    }

}
