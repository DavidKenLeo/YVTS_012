package com.dkl.auser.yvts_012;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.example.yvtc.yvtc2017111302.data.Student;

/**
 * Created by yvtc on 2017/11/13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    data data[];
    Context context;
    private View v;

    public MyAdapter(Context context, data[] data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView rv = (RecyclerView)v.findViewById(R.id.rv);
//                LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        return new ViewHolder(rv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.tv.setText(data[position].name);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
//        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
//            tv = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
