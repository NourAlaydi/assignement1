package com.example.assignement1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
 /// some passwords and username
/// username = noor , password = 123
/// username = roba , password = 111

/// i also allowed for new username and passwords to login

public class MainActivity extends AppCompatActivity {
    private EditText edt_name;
    private EditText edt_password;
    private Button bt_login;
    private CheckBox chkRememberMe;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "MyPrefs";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        edt_name = findViewById(R.id.edt_name);
        edt_password = findViewById(R.id.password);
        bt_login = findViewById(R.id.bt_login);
        chkRememberMe = findViewById(R.id.chk_remember_me); // Initialize CheckBox

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Check if "Remember Me" is checked
        if (sharedPreferences.getBoolean("RememberMe", false)) {
            String savedUsername = sharedPreferences.getString(USERNAME_KEY, "");
            String savedPassword = sharedPreferences.getString(PASSWORD_KEY, "");
            edt_name.setText(savedUsername);
            edt_password.setText(savedPassword);
            chkRememberMe.setChecked(true);
        }

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the login method
                boolean isLoggedIn = login();
                // Check if login is successful
                if (isLoggedIn) {
                    String msg = edt_name.getText().toString();
                    Intent intent = new Intent(MainActivity.this, home.class);
                    intent.putExtra("DATA", msg);
                    startActivity(intent);
                }
            }
        });
    }

private boolean login() {
    // Retrieve input from EditTexts
    String username = edt_name.getText().toString().trim();
    String password = edt_password.getText().toString().trim();

    String savedPassword = sharedPreferences.getString(username, "");

    // Check if "Remember Me" is checked
    if (chkRememberMe.isChecked()) {
        // Save username and password
        editor.putString(USERNAME_KEY, username);
        editor.putString(PASSWORD_KEY, password);
        editor.putBoolean("RememberMe", true);
        editor.apply(); // Apply changes
    } else {
        // Clear saved username and password
        editor.remove(USERNAME_KEY);
        editor.remove(PASSWORD_KEY);
        editor.putBoolean("RememberMe", false);
        editor.apply();
    }

    // Check if saved
    if (savedPassword.isEmpty()) {
        // No saved ,save the new account
        editor.putString(username, password);
        editor.apply();
        Toast.makeText(this, "New account was created", Toast.LENGTH_SHORT).show();
        return true; // successful login for new account
    } else {
        // Check if the input matches the saved password
        if (password.equals(savedPassword)) {
            // Successful login
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            return true; // Return true indicating successful login for existing account
        } else {
            // Invalid
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show();
            // Clear password field
            edt_password.setText("");
        }
    }
    return false; // login failed
}



}
