package com.rash1k.moneyflow.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rash1k.moneyflow.R;
import com.rash1k.moneyflow.adapters.DashboardPagerAdapter;
import com.rash1k.moneyflow.dialogs.AddNewExpenseDialog;
import com.rash1k.moneyflow.dialogs.AddNewIncomeDialog;
import com.rash1k.moneyflow.util.Prefs;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    //    Test
    DashboardPagerAdapter dashboardPagerAdapter;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dashboardPagerAdapter = new DashboardPagerAdapter(this, getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.vpDashboard);
        viewPager.setAdapter(dashboardPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDashBoard);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.btnDashBoardShowExpenses).setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_expenses_add:
                AddNewExpenseDialog addNewExpenseDialog = new AddNewExpenseDialog();
                addNewExpenseDialog.show(getSupportFragmentManager(), "addExpense");
                //Toast.makeText(this, "Click on expense", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_expenses_reduce:
                AddNewIncomeDialog addNewIncomeDialog = new AddNewIncomeDialog();
                addNewIncomeDialog.show(getSupportFragmentManager(), "addIncome");
                break;

            case R.id.item_user_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
        return true;
    }

    // вывод в лог данных из курсора
    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(Prefs.LOG_TAG, str);
                } while (c.moveToNext());
            }
        } else
            Log.d(Prefs.LOG_TAG, "Cursor is null");
    }

    @Override
    public void onClick(View v) {

        Cursor c;

        switch (v.getId()) {
            case R.id.btnDashBoardShowExpenses:

                Log.d(Prefs.LOG_TAG, "--- EXPENSES_NAMES Table ---");
                c = getContentResolver().query(Prefs.URI_EXPENSES_NAMES, null, null, null, null);
                logCursor(c);
                c.close();

                Intent intent = new Intent(this, ExpensesActivity.class);
                startActivity(intent);
                break;

            // TODO: 04.06.2016 Add btnDashBoardShowIncomes.
        }
    }

    public void setFragmentInfo(int position){
        switch (position){
            case DashboardPagerAdapter.FRAGMENT_CASH_FLOW:
                toolbar.setTitle(R.string.title_tab_cash_flow);
                break;
            case DashboardPagerAdapter.FRAGMENT_EXPENSES:
                toolbar.setTitle(R.string.title_tab_expenses);
                break;
            case DashboardPagerAdapter.FRAGMENT_INCOMES:
                toolbar.setTitle(R.string.title_tab_incomes);
                break;

        }
        setSupportActionBar(toolbar);

    }
}
