package com.example.firstmultiscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public myAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.firstTitle.setText(user.getFirstTitle());
        holder.firstDate.setText(user.getFirstDate());
        holder.firstCash.setText(user.getFirstCash());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstTitle, firstDate, firstCash;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstTitle = itemView.findViewById(R.id.firstTitle);
            firstDate = itemView.findViewById(R.id.firstDate);
            firstCash = itemView.findViewById(R.id.firstCash);
        }
    }
}
