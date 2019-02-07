package com.example.michal.asisstantv04.ActionCommands;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObservable;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;

import java.util.HashMap;
import java.util.Map;

public class CallingAction implements IAction {
    @Override
    public void doAction(Request request, Context context, ChatRecyclerAdapter adapter) {
        Argument argument1 = request.getArguments().get(ActionSchema.ARG_CONTACT);
        Argument argument2 = request.getArguments().get(ActionSchema.ADDED_ARG_CONTACT);
        Log.e("test222", argument1.toString());
        String contact = getNumber(argument1.argContent, context);
        Log.e("test222", contact);

        if(contact != null){
            Uri uri = Uri.parse("tel:" + contact);
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            try{
                context.startActivity(intent);
            }catch (SecurityException e){
                Log.e("SecurityException", e.getMessage());
            }
        }

    }

    public String getNumber(String contact, Context context){
        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);

        Map<String, String> contacts = new HashMap<String, String>();


        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur.moveToNext()) {

                String id = cur.getString( cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)).toLowerCase();
                String has_phone = ContactsContract.Contacts.HAS_PHONE_NUMBER;
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String contact_id = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;

                if (cur.getInt(cur.getColumnIndex(has_phone)) > 0) {
                    Cursor pCur = cr.query(uri,null,contact_id + " = ?", new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contacts.put(name.toLowerCase(), phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }
        return contacts.get(contact.toLowerCase().trim());

    }
}
