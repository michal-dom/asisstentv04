package com.example.michal.asisstantv04.Presenters;
import com.example.michal.asisstantv04.R;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.example.michal.asisstantv04.Models.Request;
import com.example.michal.asisstantv04.RequestBuilders.ActionSchema;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>  {

    private Context context;
    private List<Request> requests;

    public RecyclerAdapter(Context context, List<Request> requests) {
        this.context = context;
        this.requests = new ArrayList<>();

//        for(Request request: requests){
//            if(!request.arguments.containsValue(ActionSchema.ARG_MISSING)){
//                this.requests.add(request);
//            }
//        }

    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolder holder, int position) {

//        if(requests.get(position).arguments.containsValue(ActionSchema.ARG_MISSING)){
//            return;
//        }

        if(requests.get(position).action.equals(ActionSchema.TYPE_CALLING)){
            holder.textViewAction.setText("Dzwonie do ");
        } else if(requests.get(position).action.equals(ActionSchema.TYPE_LAUNCHING)){
            holder.textViewAction.setText("Uruchamiam ");
        } else if(requests.get(position).action.equals(ActionSchema.TYPE_SMS)) {
            holder.textViewAction.setText("Wysyłam wiadomość do ");
        } else {
            return;
        }

        if(requests.get(position).arguments.get(ActionSchema.ARG_CONTACT) != null){
            holder.textViewArg.setText(requests.get(position).arguments.get(ActionSchema.ARG_CONTACT).argContent);
        }else if(requests.get(position).arguments.get(ActionSchema.ARG_APPNAME) != null){
            holder.textViewArg.setText(requests.get(position).arguments.get(ActionSchema.ARG_APPNAME).argContent);
        } else {
            holder.textViewArg.setText("");
        }
        if(requests.get(position).arguments.get(ActionSchema.ARG_BODY) != null){
            holder.textViewText.setText(requests.get(position).arguments.get(ActionSchema.ARG_BODY).argContent);
        } else {
            holder.textViewText.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewAction;
        public TextView textViewArg;
        public TextView textViewText;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewAction = (TextView) itemView.findViewById(R.id.text_view_action);
            textViewArg = (TextView) itemView.findViewById(R.id.text_view_argument);
            textViewText = (TextView) itemView.findViewById(R.id.text_view_text);
        }
    }
}
