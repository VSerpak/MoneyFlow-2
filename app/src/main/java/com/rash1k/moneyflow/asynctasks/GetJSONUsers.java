package com.rash1k.moneyflow.asynctasks;

import android.app.Activity;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.util.Log;
import com.rash1k.moneyflow.util.Prefs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;


public class GetJSONUsers extends AsyncTask<String, Void, Cursor> {

    private Activity activity;
    private String email;
    private String password;

    public GetJSONUsers(Activity activity, String email, String password) {

    }

    @Override
    protected Cursor doInBackground(String... params) {
        JSONObject jsonObject = null;
        MatrixCursor matrixCursor  = new MatrixCursor(
                new String[]{
                        BaseColumns._ID,
                        Prefs.FN_FIELD_NAME,
                        Prefs.LN_FIELD_NAME,
                        Prefs.BIRTHDAY_FIELD_NAME,
                        Prefs.EMAIL_FIELD_NAME
                });
        Document document = null;
        try {
            document = Jsoup.connect(params[0]).get();
            Elements body = document.select("body");
            Element firstBody = body.first();
            String resElementText = firstBody.text();
            Log.d(Prefs.LOG_TAG, resElementText);

            JSONArray arrayUsersJSON = new JSONArray(resElementText);
            Log.d(Prefs.LOG_TAG, "Length - " + arrayUsersJSON.length());

            JSONObject localJson = null;
            for (int i = 0; i < arrayUsersJSON.length(); i++) {
                localJson = arrayUsersJSON.getJSONObject(i);
                localJson = localJson.getJSONObject("user_class");

                Object[] array = new Object[]{
                        localJson.getInt(BaseColumns._ID),
                        localJson.getString(Prefs.FN_FIELD_NAME),
                        localJson.getString(Prefs.LN_FIELD_NAME),
                        localJson.getString(Prefs.BIRTHDAY_FIELD_NAME),
                        localJson.getString(Prefs.EMAIL_FIELD_NAME)};
                Log.d(Prefs.LOG_TAG, Arrays.toString(array));

                matrixCursor.addRow(array);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matrixCursor;
    }

}