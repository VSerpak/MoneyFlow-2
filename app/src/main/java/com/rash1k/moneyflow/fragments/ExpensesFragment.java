package com.rash1k.moneyflow.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rash1k.moneyflow.util.Prefs;

import java.util.HashMap;

public class ExpensesFragment extends Fragment implements LoaderManager.LoaderCallbacks<HashMap<String, String>> {

    private static final String CURRENT_MONTH = "current";
    TextView tvExpensesSummary;

    HashMap<String, String> result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
        tvExpensesSummary = (TextView) view.findViewById(android.R.id.text1);

        getActivity().getSupportLoaderManager().initLoader(1, null, this);
        return view;
    }


    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {



        return new HashMapLoader(getActivity(), result);
    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {

    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }

    public  class HashMapLoader extends Loader<HashMap<String, String>> {


        public HashMapLoader(Context context, HashMap<String, String> result) {
            super(context);
            result = new HashMap<>();
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }

        @Override
        protected void onForceLoad() {
            super.onForceLoad();

            Cursor cursor = getContext().getContentResolver().query(Prefs.URI_EXPENSES, new String[]{Prefs.EXPENSES_FIELD_VOLUME}, null, null, null);
            cursor.moveToFirst();


            int value = 0;

            do {
                value += cursor.getInt(cursor.getColumnIndex(Prefs.EXPENSES_FIELD_VOLUME));
            } while (cursor.moveToNext());



            result.put(CURRENT_MONTH, Integer.toString(value));
            deliverResult(result);
        }
    }
}
