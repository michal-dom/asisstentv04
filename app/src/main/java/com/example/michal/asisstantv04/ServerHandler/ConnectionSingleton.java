package com.example.michal.asisstantv04.ServerHandler;

import android.util.Log;

public class ConnectionSingleton {
    private static final ConnectionSingleton ourInstance = new ConnectionSingleton();

    public TcpClient tcpClient;

    public static ConnectionSingleton getInstance() {
        return ourInstance;
    }

    private ConnectionSingleton() {
        this.tcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
            @Override
            public void messageReceived(String message) {
                Log.e("message", message);
            }
        });
    }
}
