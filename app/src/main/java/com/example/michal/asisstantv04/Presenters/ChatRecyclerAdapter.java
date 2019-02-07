package com.example.michal.asisstantv04.Presenters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.R;

import java.util.ArrayList;
import java.util.List;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.RecyclerViewHolder>  {


    private Context context;
    private ArrayList<String[]> balloons;

    public static final String USER = "USER_BALOON";
    public static final String APP = "APP_BALOON";

    public ChatRecyclerAdapter(Context context){
        this.context = context;
        this.balloons = ChatList.getInstance().getChat();

    }

    @NonNull
    @Override
    public ChatRecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.balloon_layout, parent, false);
        return new ChatRecyclerAdapter.RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerAdapter.RecyclerViewHolder holder, int position) {

        if(balloons.get(position) != null && balloons.get(position).length == 2){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if(balloons.get(position)[0].equals(USER) ){
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);

                holder.linearLayout.setGravity(Gravity.RIGHT);
                holder.textView.setTextColor(Color.parseColor("#d1d1d1"));
                holder.constraintLayout.setBackgroundColor(Color.parseColor("#00574B"));
            }
            if(balloons.get(position)[0].equals(APP) ){
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);

                holder.linearLayout.setGravity(Gravity.LEFT);
                holder.constraintLayout.setBackgroundColor(Color.parseColor("#d1d1d1"));

            }
            holder.textView.setText(balloons.get(position)[1]);


        }
    }

    @Override
    public int getItemCount() {
        return balloons.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public CardView cardView;
        public ConstraintLayout constraintLayout;
        public LinearLayout linearLayout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.text_balloon);
            cardView = (CardView) itemView.findViewById(R.id.balloon_view);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.balloon_layout);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.balloon_linear);
        }
    }

}
