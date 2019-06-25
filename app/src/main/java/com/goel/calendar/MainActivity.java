package com.goel.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameField;
    EditText passwordField;
    Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Init all views */
        usernameField = (EditText) findViewById(R.id.usernameField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        signinButton  = (Button) findViewById(R.id.signinButton);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
