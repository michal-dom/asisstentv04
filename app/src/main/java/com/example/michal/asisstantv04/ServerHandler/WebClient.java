package com.example.michal.asisstantv04.ServerHandler;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class WebClient extends AsyncTask<String, Void, String> {


    public static final String SERVER_IP = "192.168.35.125";
    public static final int SERVER_PORT = 5012;

    private String mServerMessage;
    private ReceiveListener listener = null;
    private boolean mRun = false;

    private Socket socket;

    public WebClient(ReceiveListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {

        try{
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            socket = new Socket(serverAddr, SERVER_PORT);

            PrintWriter mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            if (mBufferOut != null && !mBufferOut.checkError()) {
                mBufferOut.println(strings[0]);
                mBufferOut.flush();
            }

            BufferedReader mBufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            mRun = true;

            while (mRun) {
                mServerMessage = mBufferIn.readLine();
                if (mServerMessage != null) {
//                    listener.onReceive(mServerMessage);
                    mRun = false;
                }
            }

            socket.close();


        }catch (Exception e){
            Log.e("TCP", "S: Error", e);
        }
        finally {

        }
//        onPostExecute(mServerMessage);

        return mServerMessage;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (mServerMessage != null) {
            listener.onReceive(mServerMessage);
        }
    }

    public interface ReceiveListener{
        public void onReceive(String message);
    }


}
