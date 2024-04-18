package com.example.assignement1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class unite1 extends AppCompatActivity {
    static TextView txt_pts;
    private Button bt_finish;
    private ListView lst_qs;
    private ArrayList<question> qs;
    private SharedPreferences shprf_qs_ps;
    private SharedPreferences.Editor editor_shprf_qs_ps;
    private static final String PREF_NAME = "MyPrefs";
    private static final String TOTAL_POINTS_KEY = "totalPoints";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unite1);
        setupViews();
    }

    private void setupViews() {
        qs = new ArrayList<>();
        lst_qs = findViewById(R.id.lst_progs);
        bt_finish = findViewById(R.id.bt_finish);
        txt_pts = findViewById(R.id.txt_pts);

        // Initialize SharedPreferences for storing question points
        shprf_qs_ps = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor_shprf_qs_ps = shprf_qs_ps.edit();

        // Load saved points for each question
        int savedPoints1 = shprf_qs_ps.getInt("question1", 0);
        int savedPoints2 = shprf_qs_ps.getInt("question2", 0);
        int savedPoints3 = shprf_qs_ps.getInt("question3", 0);

        // Add questions to the list
        qs.add(new question("question1", savedPoints1));
        qs.add(new question("question2", savedPoints2));
        qs.add(new question("question3", savedPoints3));

        // Update and display total points
        updateTotalPoints();
        displayTotalPoints();

        // adapter for the ListView
        ArrayAdapter<question> adapter = new ArrayAdapter<>(unite1.this,
                android.R.layout.simple_list_item_1, qs);
        lst_qs.setAdapter(adapter);

        // item click events
        lst_qs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item
                question clickedQuestion = (question) parent.getItemAtPosition(position);

                // go to selected question activity
                Intent intent = null;
                if (clickedQuestion.getNum().equals("question1")) {
                    intent = new Intent(unite1.this, q1.class);
                } else if (clickedQuestion.getNum().equals("question2")) {
                    intent = new Intent(unite1.this, q2.class);
                } else if (clickedQuestion.getNum().equals("question3")) {
                    intent = new Intent(unite1.this, q3.class);
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        // finish button
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to myprogress activity
                Intent intent = new Intent(unite1.this, myprogress.class);
                intent.putExtra("PS", txt_pts.getText().toString());
                startActivity(intent);
            }
        });
    }

    // Update total points based on the points of each question
    private void updateTotalPoints() {
        int totalPoints = 0;
        for (question q : qs) {
            totalPoints += q.getPoints();
        }
        // Store updated total points in SharedPreferences
        editor_shprf_qs_ps.putInt(TOTAL_POINTS_KEY, totalPoints);
        editor_shprf_qs_ps.apply();
    }

    // Display total points in the text view
    private void displayTotalPoints() {
        String totalPoints = String.valueOf(shprf_qs_ps.getInt(TOTAL_POINTS_KEY, 0));
        txt_pts.setText("POINTS: " + totalPoints + "/10");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update and display total points when activity is resumed
        updateTotalPoints();
        displayTotalPoints();
    }
}
