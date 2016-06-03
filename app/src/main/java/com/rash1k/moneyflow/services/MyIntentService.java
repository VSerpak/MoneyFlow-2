package com.rash1k.moneyflow.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.rash1k.moneyflow.util.Prefs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyIntentService extends IntentService {

    private static final String ACTION_INSERT_EXPENSE = "com.rash1k.moneyflow.services.action.INSERT_EXPENSE";

    private static final String EXTRA_EXPENSE_NAME = "com.rash1k.moneyflow.services.extra.EXPENSE_NAME";
    private static final String EXTRA_EXPENSE_VOLUME = "com.rash1k.moneyflow.services.extra.EXPENSE_VOLUME";
    private static final String EXTRA_EXPENSE_CRITICAL = "com.rash1k.moneyflow.services.extra.EXPENSE_CRITICAL";


    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionInsertExpense(Context context, String name, Double volume, int critical) {
        Intent intent = new Intent(context, MyIntentService.class);

        intent.setAction(ACTION_INSERT_EXPENSE);
        intent.putExtra(EXTRA_EXPENSE_NAME, name);
        intent.putExtra(EXTRA_EXPENSE_VOLUME, volume);
        intent.putExtra(EXTRA_EXPENSE_CRITICAL, critical);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case ACTION_INSERT_EXPENSE:
                    final String name = intent.getStringExtra(EXTRA_EXPENSE_NAME);
                    final double volume = intent.getDoubleExtra(EXTRA_EXPENSE_VOLUME, 0);
                    final int critical = intent.getIntExtra(EXTRA_EXPENSE_CRITICAL, 0);
                    handleActionInsertExpense(name, volume, critical);
                    break;
                default:
                    throw new UnsupportedOperationException("This action isn't supported -> " + action);
            }
        }
    }

    private void handleActionInsertExpense(String name, double volume, int critical) {
        ContentValues cvExpenseName = new ContentValues();

        cvExpenseName.put(Prefs.EXPENSE_NAMES_FIELD_NAME, name);
        cvExpenseName.put(Prefs.EXPENSE_NAMES_FIELD_CRITICAL,critical);

        Uri expenseNamesUri = getContentResolver().insert(Prefs.URI_EXPENSES_NAMES, cvExpenseName);

        long insertedId = Long.parseLong(expenseNamesUri.getLastPathSegment());

        String date = new SimpleDateFormat("dd MMM yyyy").format(new Date());


        ContentValues cvExpenses = new ContentValues();

        cvExpenses.put(Prefs.EXPENSE_FIELD_DATE, date);
        cvExpenses.put(Prefs.EXPENSE_FIELD_VOLUME, volume);
        cvExpenses.put(Prefs.EXPENSE_FIELD_ID_PASSIVE, insertedId);

        getContentResolver().insert(Prefs.URI_EXPENSES, cvExpenses);
    }
}