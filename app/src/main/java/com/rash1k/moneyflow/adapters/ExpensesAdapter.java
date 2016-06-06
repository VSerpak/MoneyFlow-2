package com.rash1k.moneyflow.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rash1k.moneyflow.R;
import com.rash1k.moneyflow.util.Prefs;


public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.InnerViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public ExpensesAdapter(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }


    @Override
    public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_expenses_adapter, parent, false);
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InnerViewHolder holder, int position) {

        mCursor.moveToPosition(position);

        holder.tvName.setText(mCursor.getString(mCursor.getColumnIndex(Prefs.EXPENSE_NAMES_FIELD_NAME)));

        holder.tvVolume.setText(mCursor.getString(mCursor.getColumnIndex(Prefs.EXPENSES_FIELD_VOLUME)));
        holder.tvDate.setText(mCursor.getString(mCursor.getColumnIndex(Prefs.EXPENSES_FIELD_DATE)));

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public  class InnerViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvVolume;
        public TextView tvDate;

        public InnerViewHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.tvNameItemExpenses);
            tvVolume = (TextView) view.findViewById(R.id.tvVolumeItemExpenses);
            tvDate = (TextView) view.findViewById(R.id.tvDateItemExpenses);

        }
    }
}
