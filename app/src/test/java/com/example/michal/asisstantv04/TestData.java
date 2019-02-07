package com.example.michal.asisstantv04;

import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;

import java.util.ArrayList;
import java.util.List;

public class TestData implements ActionSchema {

    public ArrayList<Request> requests;

    public TestData(){
        requests = new ArrayList<>();

        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
        requests.add(new Request(0, "wyślij wiadomosc do Jan Kowalski", TYPE_SMS));
    }
}
