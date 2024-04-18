package com.example.assignement1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class q1 extends AppCompatActivity {
    private Button bt_finish1;
    private CheckBox chk_1t;
    private CheckBox chk_1f;
    private CheckBox chk_2t;
    private CheckBox chk_2f;
    private CheckBox chk_3t;
    private CheckBox chk_3f;
    private CheckBox chk_4t;
    private CheckBox chk_4f;
    static int points1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_q1);

        setupViews();
    }

    // Method to initialize views and set up click listener
    private void setupViews() {
        // Find views by their IDs
        bt_finish1 = findViewById(R.id.bt_finish1);
        chk_1t = findViewById(R.id.chk_1t);
        chk_1f = findViewById(R.id.chk_1f);
        chk_2t = findViewById(R.id.chk_2t);
        chk_2f = findViewById(R.id.chk_2f);
        chk_3t = findViewById(R.id.chk_3t);
        chk_3f = findViewById(R.id.chk_3f);
        chk_4t = findViewById(R.id.chk_4t);
        chk_4f = findViewById(R.id.chk_4f);

        // finish button
        bt_finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset points counter
                points1 = 0;

                // Check if both checkboxes in any row are checked
                if ((chk_1t.isChecked() && chk_1f.isChecked()) ||
                        (chk_2t.isChecked() && chk_2f.isChecked()) ||
                        (chk_3t.isChecked() && chk_3f.isChecked()) ||
                        (chk_4t.isChecked() && chk_4f.isChecked())) {
                    // Display a toast message if both checkboxes in any row are checked
                    Toast.makeText(q1.this, "Check only one box for each row", Toast.LENGTH_SHORT).show();
                } else {
                    // Increment points1 for each checked checkbox in each row
                    if (chk_1t.isChecked() && !chk_1f.isChecked()) {
                        points1 += 1;
                    }
                    if (chk_2t.isChecked() && !chk_2f.isChecked()) {
                        points1 += 1;
                    }
                    if (chk_3t.isChecked() && !chk_3f.isChecked()) {
                        points1 += 1;
                    }
                    if (chk_4t.isChecked() && !chk_4f.isChecked()) {
                        points1 += 1;
                    }

                    // go to unite1 activity
                    Intent intent = new Intent(q1.this, unite1.class);
                    intent.putExtra("POINTS1", points1);
                    startActivity(intent);

                    // Save points to SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("question1", points1);
                    editor.apply();
                }
            }
        });
    }
}
