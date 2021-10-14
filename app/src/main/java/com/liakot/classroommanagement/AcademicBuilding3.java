package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AcademicBuilding3 extends AppCompatActivity {


   /* String[] floor0RoomNo = {"Room No: 101", "Room No: 102", "Room No: 103", "Room No: 104", "Room No: 105",
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

    String[] floor3RoomNo = {"Room No: 401", "Room No: 402", "Room No: 403", "Room No: 404", "Room No: 405",
            "Room No: 406", "Room No: 407", "Room No: 408", "Room No: 409", "Room No: 410", "Room No: 411",
            "Room No: 412", "Room No: 413", "Room No: 414", "Room No: 415", "Room No: 435", "Room No: 436",
            "Room No: 437", "Room No: 438", "Room No: 439", "Room No: 440", "Room No: 441", "Room No: 442", "Room No: 443"};


    String[] floor0StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor1StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor2StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor3StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00","10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00","10:00", "11:00", "12:00", "1:00", "2:00", "3:00","10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};


    String[] floor0EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor1EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor2EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor3EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00","10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00","10:00", "11:00", "12:00", "1:00", "2:00", "3:00","10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};


    String[] floor0Enroll = {"Enroll", "Booked", "Enroll", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Enroll", "Booked", "Booked", "Enroll",
            "Enroll", "Booked", "Enroll", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Enroll", "Booked", "Booked", "Enroll"};

    String[] floor1Enroll = {"Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked",
            "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked"};

    String[] floor2Enroll = {"Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked",
            "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked"};

    String[] floor3Enroll = {"Booked", "Booked", "Enroll", "Booked", "Enroll", "Booked","Booked", "Booked", "Enroll", "Booked", "Enroll", "Booked",
            "Booked", "Booked", "Enroll", "Booked", "Enroll", "Booked","Booked", "Booked", "Enroll", "Booked", "Enroll", "Booked"};*/


    Button academic3GroundFloor, academic3FirstFloor, academic3SecondFloor,academic3ThirdFloor;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_building3);

        InitializeAll();
    }

    private void InitializeAll() {
        //--------for back button in menu bar-----------
        toolbar=findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //-----------Initialization section----------
        academic3GroundFloor = findViewById(R.id.academic3GroundFloor);
        academic3FirstFloor = findViewById(R.id.academic3FirstFloor);
        academic3SecondFloor = findViewById(R.id.academic3SecondFloor);
        academic3ThirdFloor = findViewById(R.id.academic3ThirdFloor);

        academic3GroundFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding3.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });

        academic3FirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding3.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });

        academic3SecondFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding3.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });
        academic3ThirdFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding3.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
