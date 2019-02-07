package com.example.michal.asisstantv04.RequestBuilders;

import android.content.Context;
import android.view.View;

import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;

public class MapBuilder extends RequestBuilder {
    @Override
    public boolean isReady(String arg) {
        return false;
    }

    @Override
    public void buildRequest(Context context, View view, RequestReadyCallback callback,
                             ChatRecyclerAdapter adapter) {

    }
}
