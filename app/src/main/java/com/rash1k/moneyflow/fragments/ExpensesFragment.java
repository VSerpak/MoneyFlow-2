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

import com.rash1k.moneyflow.R;
import com.rash1k.moneyflow.activities.DashboardActivity;
import com.rash1k.moneyflow.adapters.DashboardPagerAdapter;
import com.rash1k.moneyflow.util.Prefs;

import java.util.HashMap;

public class ExpensesFragment extends Fragment implements LoaderManager.LoaderCallbacks<HashMap<String, String>> {

    private static final String CURRENT_MONTH = "current";
    TextView tvCurrentFragmentExpenses;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((DashboardActivity)getActivity()).setFragmentInfo(DashboardPagerAdapter.FRAGMENT_EXPENSES);

        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        tvCurrentFragmentExpenses = (TextView) view.findViewById(R.id.tvCurrentFragmentExpenses);

        getActivity().getSupportLoaderManager().initLoader(1, null, this);
        return view;
    }


    @Override
    public Loader<HashMap<String, String>> onCreateLoader(int id, Bundle args) {


        return new HashMapLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<HashMap<String, String>> loader, HashMap<String, String> data) {
        tvCurrentFragmentExpenses.setText(data.get(CURRENT_MONTH));

    }

    @Override
    public void onLoaderReset(Loader<HashMap<String, String>> loader) {

    }

    private static class HashMapLoader extends Loader<HashMap<String, String>> {
        HashMap<String, String> result;

        public HashMapLoader(Context context) {
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
            if (cursor != null) {
                if (cursor.getCount() != 0) {
                    int value = 0;

                    do {

                        value += cursor.getInt(cursor.getColumnIndex(Prefs.EXPENSES_FIELD_VOLUME));
                    } while (cursor.moveToNext());


                    result.put(CURRENT_MONTH, Integer.toString(value));
                    deliverResult(result);
                } else {
                    result.put(CURRENT_MONTH, "0");
                }
            }
        }
    }
}
