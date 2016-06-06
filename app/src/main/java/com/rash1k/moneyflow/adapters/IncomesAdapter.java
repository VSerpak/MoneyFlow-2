package com.rash1k.moneyflow.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public class IncomesAdapter extends RecyclerView.Adapter {

    Context context;
    Cursor cursor;

    public IncomesAdapter(Context context, Cursor query) {

        this.context = context;
        cursor = query;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
