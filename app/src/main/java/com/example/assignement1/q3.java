package com.example.assignement1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class q3 extends AppCompatActivity {
    private Spinner sp1;
    private Spinner sp2;
    Button bt_finish3;
    static  int points3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_q3);

        setupviews();

        // Populate spinner 1 and spinner 2 with data
        populateSpinner1();
        populateSpinner2();
    }

    // Method to initialize views and set up click listener
    private void  setupviews (){
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        bt_finish3 = findViewById(R.id.bt_finish3);

        // finish button
        bt_finish3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points3=0;

                // Get the selected words from the spinners
                String word1 = sp1.getSelectedItem().toString().trim();
                String word2 = sp2.getSelectedItem().toString().trim();

                // Check if the selected words are correct and update points3 accordingly
                if(word1.equals("Grateful")){
                    points3+=1;
                }
                if(word2.equals("Genius")){
                    points3+=1;
                }

                // Save points3 to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("question3", points3);
                editor.apply();

                // to unite1 activity
                Intent intent = new Intent(q3.this, unite1.class);
                intent.putExtra("POINTS3", points3);
                startActivity(intent);
            }
        });
    }

    // Method to populate spinner 1 with data
    private void populateSpinner1() {
        String[] words = {"Genius","Grateful","Gift"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(q3.this,
                android.R.layout.simple_spinner_item, words);
        sp1.setAdapter(adapter);
    }

    // Method to populate spinner 2 with data
    private void populateSpinner2() {
        String[] words = {"Genius","Genre","Glad"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(q3.this,
                android.R.layout.simple_spinner_item, words);
        sp2.setAdapter(adapter);
    }
}
