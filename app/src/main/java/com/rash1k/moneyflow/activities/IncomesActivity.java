package com.rash1k.moneyflow.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rash1k.moneyflow.R;
import com.rash1k.moneyflow.adapters.IncomesAdapter;
import com.rash1k.moneyflow.util.Prefs;

public class IncomesActivity extends AppCompatActivity {

    private RecyclerView rvIncomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvIncomes = (RecyclerView) findViewById(R.id.rvIncomes);

        rvIncomes.setLayoutManager(new LinearLayoutManager(this));

        rvIncomes.setAdapter(new IncomesAdapter(this,getContentResolver().query(Prefs.URI_ALL_INCOMES,null,null,null,null)));
    }
}
