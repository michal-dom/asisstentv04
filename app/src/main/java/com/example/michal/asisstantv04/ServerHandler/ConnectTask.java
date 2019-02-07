package com.example.michal.asisstantv04.ServerHandler;

import android.os.AsyncTask;
import android.util.Log;

public class ConnectTask extends AsyncTask<String, String, TcpClient> {

    private TcpClient tcpClient;

    public ConnectTask(TcpClient tcpClient){
        this.tcpClient = tcpClient;
    }

    @Override
    protected TcpClient doInBackground(String... strings) {
        tcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
            @Override
            public void messageReceived(String message) {
                publishProgress(message);
            }
        });
        tcpClient.run(strings[0]);
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        Log.e("test", values[0]);
    }
}
