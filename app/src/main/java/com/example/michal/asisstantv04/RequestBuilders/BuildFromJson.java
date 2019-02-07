package com.example.michal.asisstantv04.RequestBuilders;

import android.util.Log;

import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class BuildFromJson {


    public static Request buildRequest(String json){
        Request newRequest = new Request();
        try {


            JSONObject object = new JSONObject(json);
            newRequest.setText(object.getString("utterance"));
            newRequest.setAction(object.getString("action"));

//            Log.e("json", object.getString("arguments"));

            JSONArray array = new JSONArray(object.getString("arguments"));

            for (int i = 0; i < array.length(); i++) {
                JSONObject j = array.getJSONObject(i);

                Argument arg = new Argument(0, 0, j.getString("argument"), j.getString("type"));
                newRequest.addArgument(arg);

            }

            Log.e("newRequest", newRequest.toString());

        }catch (Exception e){
            Log.e("re", e.toString());
        }


        return newRequest;
    }
}
