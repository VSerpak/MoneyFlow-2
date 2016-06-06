package com.rash1k.moneyflow.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rash1k.moneyflow.util.Prefs;


public class DBHelper extends SQLiteOpenHelper {

     /*Table expenses
    - id
    - id passive - id from table passive
    -volume - volume of money
    -date -date when expenses made
    * */

    private static final String CREATE_TABLE_EXPENSES = String.format(
            "create table " + Prefs.TABLE_NAME_EXPENSES
                    + " ( %s integer primary key autoincrement, %s integer," +
                    " %s double, %s text);",
            Prefs.FIELD_ID,
            Prefs.EXPENSES_FIELD_ID_PASSIVE,
            Prefs.EXPENSES_FIELD_VOLUME,
            Prefs.EXPENSES_FIELD_DATE);

    /*
Table expense_names
- _id
- name - name of expense
- critical - is it necessary to make the expense constantly
*/

    private static final String CREATE_TABLE_EXPENSE_NAMES = String.format(
            "create table " + Prefs.TABLE_NAME_EXPENSE_NAMES
                    + " ( %s integer primary key autoincrement, %s text, %s integer);",
            Prefs.FIELD_ID,
            Prefs.EXPENSE_NAMES_FIELD_NAME,
            Prefs.EXPENSE_NAMES_FIELD_CRITICAL);

     /*Table incomes
    - id
    - id passive - id from table passive
    -volume - volume of money
    -date -date when incomes made
    * */

    private static final String CREATE_TABLE_INCOMES = String.format(
            "CREATE TABLE %s (%s integer primary key autoincrement, %s integer, %s double, %s text);",
            Prefs.TABLE_NAME_INCOMES,
            Prefs.FIELD_ID,
            Prefs.INCOMES_FIELD_ID_PASSIVE,
            Prefs.INCOMES_FIELD_VOLUME,
            Prefs.INCOMES_FIELD_DATA);

                 /*
    Table income_names
    - _id
    - name - name of income
    */

    private static final String CREATE_TABLE_INCOME_NAMES = String.format(
            "CREATE TABLE %s (%s integer primary key autoincrement, %s text);",
            Prefs.TABLE_NAME_INCOME_NAMES,
            Prefs.FIELD_ID,
            Prefs.INCOME_NAMES_FIELD_NAME);


    public DBHelper(Context context, int version) {
        super(context, Prefs.DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXPENSES);
        db.execSQL(CREATE_TABLE_EXPENSE_NAMES);
        db.execSQL(CREATE_TABLE_INCOMES);
        db.execSQL(CREATE_TABLE_INCOME_NAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Prefs.LOG_WARN_SQL, "onUpgrade: " + oldVersion + " on the version: " + newVersion);

        if (oldVersion < newVersion) {
            db.execSQL(CREATE_TABLE_EXPENSE_NAMES);
            db.execSQL(CREATE_TABLE_INCOMES);
            db.execSQL(CREATE_TABLE_INCOME_NAMES);
        } else {
            Log.d(Prefs.LOG_TAG, "You have current version");
        }
    }
}
