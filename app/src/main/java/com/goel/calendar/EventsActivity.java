package com.goel.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity implements View.OnClickListener {
    ListView eventsListView;
    Button createButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        this.eventsListView = findViewById(R.id.eventsListView);
        this.createButton = findViewById(R.id.createButton);
        this.createButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.fetchEventsAndCreateList();
    }

    private void fetchEventsAndCreateList() {
        db.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                EventsActivity activity = EventsActivity.this;

                if (task.isSuccessful()) {
                    ArrayList<Event> events = new ArrayList<Event>();
                    for (DocumentSnapshot snapshot : task.getResult().getDocuments())
                        events.add(snapshot.toObject(Event.class));

                    activity.eventsListView.setAdapter(new EventsListAdapter(activity, events));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
