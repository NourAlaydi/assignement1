package com.example.assignement1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class home extends AppCompatActivity {
    private TextView txt_welcome;
    private Button bt_go;
    private Spinner sp_units;

    // SharedPreferences variables
    private static final String PREF_NAME = "MyPrefs";
    private static final String DATA = "WelcomeMsg";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        setupViews();
        populateSpinner();
    }

    // Method to set up views and event listeners
    private void setupViews() {
        sp_units = findViewById(R.id.sp_units);
        bt_go = findViewById(R.id.bt_go);
        txt_welcome = findViewById(R.id.txt_welcome); // Initialize txt_welcome here

        // go button , to unite1 view
        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedUnit = sp_units.getSelectedItem().toString();
                // Check if the selected unit is Unit 1
                if (selectedUnit.equals("Unit 1")) {
                    Intent intent = new Intent(home.this, unite1.class);
                    startActivity(intent);
                } else {
                    // only Unit 1 is supported
                    Toast.makeText(home.this, "Only Unit 1 is supported", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Retrieve the welcome message from SharedPreferences
        String name = getIntent().getStringExtra("DATA");
        editor = sharedPreferences.edit();
        editor.putString(DATA, "Welcome " + name);
        editor.apply();

        txt_welcome.setText("Welcome " + name);
    }

    // Method to populate the spinner with unit options
    // only unite #1 works now , other units for future :)
    private void populateSpinner() {
        List<String> units = Arrays.asList("Unit 1", "Unit 2", "Unit 3", "Unit 4", "Unit 5", "Unit 6", "Unit 7", "Unit 8");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(home.this,
                android.R.layout.simple_spinner_item, units);
        sp_units.setAdapter(adapter);
    }
}
