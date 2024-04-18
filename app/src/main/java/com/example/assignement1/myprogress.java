package com.example.assignement1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class myprogress extends AppCompatActivity {
    private ListView lst_progs;
    private ArrayList<String> progressList;
    private ArrayAdapter<String> adapter;
    private ImageButton imbt_back_prog_home;
    private SharedPreferences shprf_progs;
    private SharedPreferences.Editor editor_shprf_prog;
    private static final String PREF_NAME = "MyPrefsProg";
    private static String POINTS_KEY = "totalPoints";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_myprogress);
        setupViews();
    }

    // Method to set up views and event listeners
    private void setupViews() {
        imbt_back_prog_home = findViewById(R.id.imbt_back_prog_home);
        lst_progs = findViewById(R.id.lst_progs);

        progressList = new ArrayList<>();

        // Initialize SharedPreferences
        shprf_progs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor_shprf_prog= shprf_progs.edit();

        // Get points from intent
        String points = getIntent().getStringExtra("PS");
        POINTS_KEY=points;

        // Load saved points for each unit
        String savedPoints1 = shprf_progs.getString("unit1", points);
        String savedPoints2 = shprf_progs.getString("unit2", "POINTS:0/10");
        String savedPoints3 = shprf_progs.getString("unit3", "POINTS:0/10");
        String savedPoints4 = shprf_progs.getString("unit4", "POINTS:0/10");
        String savedPoints5 = shprf_progs.getString("unit5", "POINTS:0/10");

        // Add unit progress to the list , it's only a not real sample , because not all units are done
        progressList.add("unit1    " +points);
        progressList.add("unit2    " +savedPoints2);
        progressList.add("unit3    " +savedPoints3);
        progressList.add("unit4    " +savedPoints4);
        progressList.add("unit5    " +savedPoints5);

        // adapter for the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(myprogress.this,
                android.R.layout.simple_list_item_1, progressList);
        lst_progs.setAdapter(adapter);

        // Set up click listener for the back button
        imbt_back_prog_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back to unite1 activity
                Intent intent = new Intent(myprogress.this, unite1.class);
                startActivity(intent);
            }
        });
    }
}
