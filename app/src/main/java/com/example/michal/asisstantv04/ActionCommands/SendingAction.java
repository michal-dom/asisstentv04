package com.example.michal.asisstantv04.ActionCommands;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.SmsManager;

import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.ChatList;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;

import java.util.HashMap;
import java.util.Map;

public class SendingAction implements IAction {
    @Override
    public void doAction(Request request, Context context, ChatRecyclerAdapter adapter) {
        Argument argument1;
        Argument argument2;
        if(request.getArguments().containsKey(ActionSchema.ARG_CONTACT)){
            argument1 = request.getArguments().get(ActionSchema.ARG_CONTACT);
        }else{
            argument1 = request.getArguments().get(ActionSchema.ADDED_ARG_CONTACT);
        }

        if(request.getArguments().containsKey(ActionSchema.ARG_BODY)){
            argument2 = request.getArguments().get(ActionSchema.ARG_BODY);
        }else{
            argument2 = request.getArguments().get(ActionSchema.ADDED_ARG_BODY);
        }
        String contact = getNumber(argument1.argContent, context);

        String[] strings1 = {ChatRecyclerAdapter.APP, "Wysyłam wiadomość do " + argument1.argContent};
        ChatList.getInstance().addItem(strings1);

        ChatList.getInstance().resetList();

        adapter.notifyItemInserted(ChatList.getInstance().getNotifier());

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(contact, null, argument2.argContent, null, null);



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
