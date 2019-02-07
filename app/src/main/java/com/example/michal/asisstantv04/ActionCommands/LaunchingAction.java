package com.example.michal.asisstantv04.ActionCommands;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.example.michal.asisstantv04.Models.Argument;
import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.Presenters.ChatList;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaunchingAction implements IAction {
    @Override
    public void doAction(Request request, Context context, ChatRecyclerAdapter adapter) {
        Argument argument = request.getArguments().get(ActionSchema.ARG_APPNAME);
        String val = getApp(context, argument.argContent);



        if (val != null) {
            Intent launch = context.getPackageManager().getLaunchIntentForPackage(val);
            if(launch != null){
                String[] strings = {ChatRecyclerAdapter.APP, "Uruchamiam: " + argument.argContent};
                ChatList.getInstance().addItem(strings);

                adapter.notifyItemInserted(ChatList.getInstance().getNotifier());
                context.startActivity(launch);
            }
        }
    }

    private String getApp(Context context, String app_name){
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appsList = context.getPackageManager().queryIntentActivities(mainIntent, 0);


        final PackageManager manager = context.getPackageManager();

        Map<String, String> apps = new HashMap<String, String>();

        for (ResolveInfo info: appsList ) {
//            Log.e("app_name", info.loadLabel(manager).toString().toLowerCase())
            apps.put(info.loadLabel(manager).toString().toLowerCase(), info.activityInfo.packageName);
        }
        String val = apps.get(app_name.toLowerCase().trim());

        return val;
    }
}
