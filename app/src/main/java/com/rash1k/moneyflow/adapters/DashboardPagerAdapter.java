package com.rash1k.moneyflow.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rash1k.moneyflow.R;
import com.rash1k.moneyflow.fragments.ExpensesFragment;


public class DashboardPagerAdapter extends FragmentPagerAdapter {


    Context context;

    public DashboardPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        DefaultFragment defaultFragment;
        Bundle argBundle;
        switch (position) {
            case 0:
                defaultFragment = new DefaultFragment();
                argBundle = new Bundle();
                argBundle.putString(DefaultFragment.KEY_NAME, context.getResources().getString(R.string.title_tab_cash_flow));
                defaultFragment.setArguments(argBundle);
                return defaultFragment;

            case 1:
                return new ExpensesFragment();
            case 2:
                defaultFragment = new DefaultFragment();
                argBundle = new Bundle();
                argBundle.putString(DefaultFragment.KEY_NAME, context.getResources().getString(R.string.title_tab_incomes));
                defaultFragment.setArguments(argBundle);
                return defaultFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:

                return context.getResources().getString(R.string.title_tab_cash_flow);

            case 1:
                return context.getResources().getString(R.string.title_tab_expenses);
            case 2:
                return context.getResources().getString(R.string.title_tab_incomes);
        }

        return super.getPageTitle(position);
    }

    public static class DefaultFragment extends Fragment {
        private static final String KEY_NAME = "name";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
            String name = getArguments().getString(KEY_NAME);
            ((TextView) view.findViewById(android.R.id.text1)).setText(name);

            return view;
        }
    }
}
