package com.example.michal.asisstantv04.ServerHandler;

import android.os.AsyncTask;
import android.util.Log;

public class SendMessageTask extends AsyncTask<String, Void, Void> {

    private TcpClient tcpClient;

    public SendMessageTask(TcpClient tcpClient){
        this.tcpClient = tcpClient;
    }

    @Override
    protected Void doInBackground(String... strings) {
        Log.e("back", strings[0]);
        this.tcpClient.sendMessage(strings[0]);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }
}
