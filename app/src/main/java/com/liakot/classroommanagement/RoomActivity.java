package com.liakot.classroommanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RoomActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView roomListView;

    String[] roomNo;
    String[] startTime;
    String[] endTime;
    String[] enrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //------receive the data from parent activity----------
        Intent intent = getIntent();
        roomNo = intent.getStringArrayExtra("room no");
        startTime = intent.getStringArrayExtra("start time");
        endTime = intent.getStringArrayExtra("end time");
        enrol = intent.getStringArrayExtra("enroll or booked");

        InitializeAll();
    }

    private void InitializeAll() {
        roomListView = findViewById(R.id.roomListView);
        MyAdapter adapter = new MyAdapter(this, roomNo, startTime, endTime, enrol);
        roomListView.setAdapter(adapter);

        //------------Toolbar Back Button----------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    //------------------Create Custom Adapter----------
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String[] room;
        String[] startTime;
        String[] endTime;
        String[] enroll;

        MyAdapter(Context context1, String[] room, String[] startTime, String[] endTime, String[] enroll) {
            super(context1, R.layout.activity_view, R.id.roomNoTextView, room);
            this.context = context1;
            this.room = room;
            this.startTime = startTime;
            this.endTime = endTime;
            this.enroll = enroll;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

            assert layoutInflater != null;
            @SuppressLint("ViewHolder") View roomView = layoutInflater.inflate(R.layout.activity_view, parent, false);

            TextView roomNoText = roomView.findViewById(R.id.roomNoTextView);
            TextView startTimeText = roomView.findViewById(R.id.startTimeTextView);
            TextView endTimeText = roomView.findViewById(R.id.endTimeTextView);
            Button enrollButton = roomView.findViewById(R.id.enrollButton);


            roomNoText.setText(roomNo[position]);
            startTimeText.setText("Start : "+startTime[position]);
            endTimeText.setText("End : "+endTime[position]);
            enrollButton.setText(enroll[position]);
            if (enroll[position].equals("Booked")) {
                enrollButton.setBackgroundResource(R.drawable.button_design_red);
                startTimeText.setText("Start : 00:00");
                endTimeText.setText("End : 00:00");

                enrollButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RoomActivity.this, Booked_Information.class);
                        startActivity(intent);
                    }
                });
            } else {
                enrollButton.setBackgroundResource(R.drawable.button_design1);
                enrollButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RoomActivity.this, BookedUserInformation.class);
                        startActivity(intent);
                    }
                });
            }
            return roomView;
        }
    }

    //---------------------For Toolbar Back Button------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
