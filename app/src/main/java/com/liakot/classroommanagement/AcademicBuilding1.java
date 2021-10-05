package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AcademicBuilding1 extends AppCompatActivity {
    Toolbar toolbar;

    String[] floor0RoomNo = {"Room No: 101", "Room No: 102", "Room No: 103", "Room No: 104", "Room No: 105",
            "Room No: 106", "Room No: 107", "Room No: 108", "Room No: 109", "Room No: 110", "Room No: 111",
            "Room No: 112", "Room No: 113", "Room No: 114", "Room No: 115", "Room No: 135", "Room No: 136",
            "Room No: 137", "Room No: 138", "Room No: 139", "Room No: 140", "Room No: 141", "Room No: 142", "Room No: 143"};

    String[] floor1RoomNo = {"Room No: 201", "Room No: 202", "Room No: 203", "Room No: 204", "Room No: 205",
            "Room No: 206", "Room No: 207", "Room No: 208", "Room No: 209", "Room No: 210", "Room No: 211",
            "Room No: 212", "Room No: 213", "Room No: 214", "Room No: 215", "Room No: 235", "Room No: 236",
            "Room No: 237", "Room No: 238", "Room No: 239", "Room No: 240", "Room No: 241", "Room No: 242", "Room No: 243"};

    String[] floor2RoomNo = {"Room No: 301", "Room No: 302", "Room No: 303", "Room No: 304", "Room No: 305",
            "Room No: 306", "Room No: 307", "Room No: 308", "Room No: 309", "Room No: 310", "Room No: 311",
            "Room No: 312", "Room No: 313", "Room No: 314", "Room No: 315", "Room No: 335", "Room No: 336",
            "Room No: 337", "Room No: 338", "Room No: 339", "Room No: 340", "Room No: 341", "Room No: 342", "Room No: 343"};


    String[] floor0StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor1StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor2StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};


    String[] floor0EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor1EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor2EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};


    String[] floor0Enroll = {"Enroll", "Booked", "Enroll", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Enroll", "Booked", "Booked", "Enroll",
            "Enroll", "Booked", "Enroll", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Enroll", "Booked", "Booked", "Enroll"};

    String[] floor1Enroll = {"Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked",
            "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked"};

    String[] floor2Enroll = {"Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked",
            "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked"};

    Button academic1GroundFloor, academic1FirstFloor, academic1SecondFloor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_building1);

        InitializeAll();
    }

    private void InitializeAll() {
        academic1GroundFloor = findViewById(R.id.academic1GroundFloor);
        academic1FirstFloor = findViewById(R.id.academic1FirstFloor);
        academic1SecondFloor = findViewById(R.id.academic1SecondFloor);

        academic1GroundFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcademicBuilding1.this, RoomActivity.class);
                intent.putExtra("room no", floor0RoomNo);
                intent.putExtra("start time", floor0StartTime);
                intent.putExtra("end time", floor0EndTime);
                intent.putExtra("enroll or booked", floor0Enroll);
                startActivity(intent);
            }
        });

        academic1FirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcademicBuilding1.this, RoomActivity.class);
                intent.putExtra("room no", floor1RoomNo);
                intent.putExtra("start time", floor1StartTime);
                intent.putExtra("end time", floor1EndTime);
                intent.putExtra("enroll or booked", floor1Enroll);
                startActivity(intent);
            }
        });

        academic1SecondFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcademicBuilding1.this, RoomActivity.class);
                intent.putExtra("room no", floor2RoomNo);
                intent.putExtra("start time", floor2StartTime);
                intent.putExtra("end time", floor2EndTime);
                intent.putExtra("enroll or booked", floor2Enroll);
                startActivity(intent);
            }
        });


        //------------for back button in menu bar----------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
