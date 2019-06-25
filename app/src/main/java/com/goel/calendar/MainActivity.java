package com.goel.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailField;
    EditText passwordField;
    Button signinButton;
    Button createAccountButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        /* Init all views */
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        signinButton = (Button) findViewById(R.id.signinButton);
        signinButton.setOnClickListener(this);

        createAccountButton = (Button) findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == createAccountButton) {
            RegisterActivity registerActivity = new RegisterActivity();
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else if (view == signinButton) {
            firebaseAuth.signInWithEmailAndPassword(emailField.getText().toString(), passwordField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Activity activity = MainActivity.this;
                    if (task.isSuccessful()) {
                        Toast.makeText(activity, R.string.login_success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, EventsActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(activity, R.string.error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
