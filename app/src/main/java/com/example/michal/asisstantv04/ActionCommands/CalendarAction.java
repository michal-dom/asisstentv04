package com.example.michal.asisstantv04.ActionCommands;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.ChatList;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;

import java.util.Calendar;
import java.util.Date;

public class CalendarAction implements IAction {


    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    private Context context;

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;


    @Override
    public void doAction(Request request, Context context, ChatRecyclerAdapter adapter) {

        Argument argument1;
        Argument argument2;
        if(request.getArguments().containsKey(ActionSchema.ARG_DATE)){
            argument1 = request.getArguments().get(ActionSchema.ARG_DATE);
        }else{
            argument1 = request.getArguments().get(ActionSchema.ADDED_ARG_DATE);
        }

        if(request.getArguments().containsKey(ActionSchema.ARG_TITLE)){
            argument2 = request.getArguments().get(ActionSchema.ARG_TITLE);
        }else{
            argument2 = request.getArguments().get(ActionSchema.ADDED_ARG_TITLE);
        }


        long calID = 3;


        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, getTime(argument1.argContent));
        values.put(CalendarContract.Events.DTEND, getTime(argument1.argContent));
        values.put(CalendarContract.Events.TITLE, argument2.argContent);
        values.put(CalendarContract.Events.DESCRIPTION, argument2.argContent);
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");
        long eventID = 0;
        try {
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            eventID = Long.parseLong(uri.getLastPathSegment());

            String[] strings = {ChatRecyclerAdapter.APP, "Dodaję wydarzenie" };
            ChatList.getInstance().addItem(strings);

            adapter.notifyItemInserted(ChatList.getInstance().getNotifier());

        }catch (SecurityException e) {
            Log.e("ex", e.toString());
        }

    }

    private long getTime(String date){
        int day_of_week = 0;

        if(date.toLowerCase().contains("ponied")){
            day_of_week = Calendar.MONDAY;
        }
        if(date.toLowerCase().contains("wtor")){
            day_of_week = Calendar.TUESDAY;
        }
        if(date.toLowerCase().contains("środ")){
            day_of_week = Calendar.WEDNESDAY;
        }
        if(date.toLowerCase().contains("czwart")){
            day_of_week = Calendar.THURSDAY;
        }
        if(date.toLowerCase().contains("piąt")){
            day_of_week = Calendar.FRIDAY;
        }
        if(date.toLowerCase().contains("sobot")){
            day_of_week = Calendar.SATURDAY;
        }
        if(date.toLowerCase().contains("niedz")){
            day_of_week = Calendar.SUNDAY;
        }
        Calendar date1 = Calendar.getInstance();
        Date currentTime = Calendar.getInstance().getTime();
        date1.setTime(currentTime);
        while (date1.get(Calendar.DAY_OF_WEEK) != day_of_week) {
            date1.add(Calendar.DATE, 1);
        }
        return date1.getTimeInMillis();

    }
}
