package com.example.assignement1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class q2 extends AppCompatActivity {
    private Button bt_finish2;
    private RadioGroup rdg1;
    private RadioGroup rdg2;
    private RadioGroup rdg3;
    private RadioGroup rdg4;
    static int points2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_q2);
        setupViews();
    }

    private void setupViews() {
        // Find views by their IDs
        bt_finish2 = findViewById(R.id.bt_finish2);
        rdg1 = findViewById(R.id.rdg1);
        rdg2 = findViewById(R.id.rdg2);
        rdg3 = findViewById(R.id.rdg3);
        rdg4 = findViewById(R.id.rdg4);

        // Set click listener for the finish button
        bt_finish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                points2 = 0;

                // Get the ID of the checked radio button in each radio group
                int id1 = rdg1.getCheckedRadioButtonId();
                int id2 = rdg2.getCheckedRadioButtonId();
                int id3 = rdg3.getCheckedRadioButtonId();
                int id4 = rdg4.getCheckedRadioButtonId();

                // Check each radio button and increment points2 accordingly
                if (id1 != -1) {
                    RadioButton rd = findViewById(id1);
                    if (rd.getText().toString().trim().equals("pleased")) {
                        points2 += 1;
                    }
                }
                if (id2 != -1) {
                    RadioButton rd = findViewById(id2);
                    if (rd.getText().toString().trim().equals("ages")) {
                        points2 += 1;
                    }
                }
                if (id3 != -1) {
                    RadioButton rd = findViewById(id3);
                    if (rd.getText().toString().trim().equals("bow")) {
                        points2 += 1;
                    }
                }
                if (id4 != -1) {
                    RadioButton rd = findViewById(id4);
                    if (rd.getText().toString().trim().equals("important")) {
                        points2 += 1;
                    }
                }

                // Save points2 to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("question2", points2);
                editor.apply();

                // go to unite1 activity & send points
                Intent intent = new Intent(q2.this, unite1.class);
                intent.putExtra("POINTS2", points2);
                startActivity(intent);
            }
        });
    }
}
