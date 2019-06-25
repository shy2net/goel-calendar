package com.goel.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {
    CalendarView calendarView;
    EditText descriptionView;
    Button saveButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                selectedDate = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
            }
        });
        descriptionView = findViewById(R.id.descriptionView);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String description = descriptionView.getText().toString();

        Map<String, Object> event = new HashMap<>();
        event.put("date", this.selectedDate);
        event.put("description", description);

        db.collection("events").add(event).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Activity calendarActivity = CalendarActivity.this;

                if (task.isSuccessful()) {
                    Toast.makeText(calendarActivity, R.string.event_saved, Toast.LENGTH_LONG).show();
                    calendarActivity.finish();
                } else {
                    Toast.makeText(calendarActivity, R.string.error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
