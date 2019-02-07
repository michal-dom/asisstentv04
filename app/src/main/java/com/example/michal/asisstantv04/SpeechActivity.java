package com.example.michal.asisstantv04;

import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.michal.asisstantv04.Presenters.ChatList;
import com.example.michal.asisstantv04.Presenters.ChatRecyclerAdapter;
import com.example.michal.asisstantv04.Presenters.CustomRecognitionListener;
import com.example.michal.asisstantv04.Presenters.RecyclerListPresenter;
import com.example.michal.asisstantv04.Presenters.SpeechActivityPresenter;

import java.util.ArrayList;

public class SpeechActivity extends AppCompatActivity {

    public ArrayList<String[]> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        TextView textView = (TextView) findViewById(R.id.result_text);
        View v = this.findViewById(android.R.id.content).getRootView();

        RecyclerListPresenter listPresenter = new RecyclerListPresenter(this);
        ChatRecyclerAdapter adapter = listPresenter.onRecyclerListCreate();

        SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        CustomRecognitionListener recognitionListener = new CustomRecognitionListener(this, v, adapter);
//        CustomRecognitionListener recognitionListener = new CustomRecognitionListener(this, recognizer, textView, R.id.fab, R.id.progress_bar);
        recognizer.setRecognitionListener(recognitionListener);
        SpeechActivityPresenter presenter = new SpeechActivityPresenter(SpeechActivity.this);
        presenter.initViews(R.id.fab, R.id.progress_bar, recognizer);



    }
}
