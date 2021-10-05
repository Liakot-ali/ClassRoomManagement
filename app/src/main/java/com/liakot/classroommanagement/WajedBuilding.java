package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WajedBuilding extends AppCompatActivity {


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

    String[] floor3RoomNo = {"Room No: 401", "Room No: 402", "Room No: 403", "Room No: 404", "Room No: 405",
            "Room No: 406", "Room No: 407", "Room No: 408", "Room No: 409", "Room No: 410", "Room No: 411",
            "Room No: 412", "Room No: 413", "Room No: 414", "Room No: 415", "Room No: 435", "Room No: 436",
            "Room No: 437", "Room No: 438", "Room No: 439", "Room No: 440", "Room No: 441", "Room No: 442", "Room No: 443"};

    String[] floor4RoomNo = {"Room No: 501", "Room No: 502", "Room No: 503", "Room No: 504", "Room No: 505",
            "Room No: 506", "Room No: 507", "Room No: 508", "Room No: 509", "Room No: 510", "Room No: 511",
            "Room No: 512", "Room No: 513", "Room No: 514", "Room No: 515", "Room No: 535", "Room No: 536",
            "Room No: 537", "Room No: 538", "Room No: 539", "Room No: 540", "Room No: 541", "Room No: 542", "Room No: 543"};


    String[] floor0StartTime = {"00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00",
            "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00"};

    String[] floor1StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor2StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor3StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor4StartTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};


    String[] floor0EndTime = {"00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00",
            "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00"};

    String[] floor1EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor2EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor3EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};

    String[] floor4EndTime = {"10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00",
            "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00"};


    //    List<String> floor0Enroll=new ArrayList<>();
    String[] floor0Enroll = {"Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll",
            "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll", "Enroll"};

    String[] floor1Enroll = {"Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked",
            "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Booked"};

    String[] floor2Enroll = {"Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked",
            "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked", "Enroll", "Booked", "Enroll", "Enroll", "Booked", "Booked"};

    String[] floor3Enroll = {"Booked", "Booked", "Enroll", "Booked", "Enroll", "Booked", "Booked", "Booked", "Enroll", "Booked", "Enroll", "Booked",
            "Booked", "Booked", "Enroll", "Booked", "Enroll", "Booked", "Booked", "Booked", "Enroll", "Booked", "Enroll", "Booked"};

    String[] floor4Enroll = {"Enroll", "Booked", "Enroll", "Booked", "Enroll", "Booked", "Enroll", "Booked", "Enroll", "Booked", "Enroll", "Booked",
            "Enroll", "Booked", "Enroll", "Booked", "Enroll", "Booked", "Enroll", "Booked", "Enroll", "Booked", "Enroll", "Booked"};

    Button wGroundFloor, wFirstFloor, wSecondFloor, wThirdFloor, wForthFloor;
    Toolbar toolbar;

    DatabaseReference allBuildings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wajed_building);
        InitializeAll();
    }

    private void InitializeAll() {

        allBuildings = FirebaseDatabase.getInstance().getReference("All Buildings");

        DatabaseReference wajedBuilding = allBuildings.child("Wajed Building");

        wGroundFloor = findViewById(R.id.wGroundFloor);
        wFirstFloor = findViewById(R.id.wajedFirstFloor);
        wSecondFloor = findViewById(R.id.wajedSecondFloor);
        wThirdFloor = findViewById(R.id.wajedThirdFloor);
        wForthFloor = findViewById(R.id.wajedForthFloor);

        DatabaseReference floor0 = wajedBuilding.child("Floor0");

        final String[] groundStart = new String[100000], groundEnd = new String[100000], groundEnroll = new String[100000];


        int i = 0;
        for (String room : floor0RoomNo) {
            RoomManagement obj1 = new RoomManagement(floor0RoomNo[i], floor0StartTime[i], floor0EndTime[i], floor0Enroll[i]);

            floor0.child(room).setValue(obj1);
//            floor0.child(room).child("Room").setValue(floor0RoomNo[i]);
//            floor0.child(room).child("Start").setValue(groundStart[i]);
//            floor0.child(room).child("End").setValue(groundEnd[i]);
//            floor0.child(room).child("Status").setValue(groundEnroll[i]);
            i++;
        }

        floor0.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    RoomManagement newObj= dataSnapshot.child(floor0RoomNo[i]).getValue(RoomManagement.class);
//                    assert newObj != null;
                    groundStart[i] = dataSnapshot.child(floor0RoomNo[i]).child("Start").getValue(String.class);
                    groundEnd[i] = dataSnapshot.child(floor0RoomNo[i]).child("End").getValue(String.class);
                    groundEnroll[i] = dataSnapshot.child(floor0RoomNo[i]).child("Status").getValue(String.class);

                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        wGroundFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("room no", floor0RoomNo);
                intent.putExtra("start time", floor0StartTime);
                intent.putExtra("end time", floor0EndTime);
                intent.putExtra("enroll or booked", floor0Enroll);
                startActivity(intent);
            }
        });

         /*
        wFirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference floor1 = wajedBuilding.child("Floor 1");
                int i = 0;
                for (String s : floor1RoomNo) {
                    RoomManagement roomManagement = new RoomManagement(floor1RoomNo[i], floor1StartTime[i], floor1EndTime[i], floor1Enroll[i]);
                    floor1.child(s).setValue(roomManagement);
                    i++;
                }
                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("room no", floor1RoomNo);
                intent.putExtra("start time", floor1StartTime);
                intent.putExtra("end time", floor1EndTime);
                intent.putExtra("enroll or booked", floor1Enroll);
                startActivity(intent);
            }
        });

        wSecondFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference floor2 = wajedBuilding.child("Floor 2");
                int i = 0;
                for (String s : floor2RoomNo) {
                    RoomManagement roomManagement = new RoomManagement(floor2RoomNo[i], floor2StartTime[i], floor2EndTime[i], floor2Enroll[i]);
                    floor2.child(s).setValue(roomManagement);
                    i++;
                }
                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("room no", floor2RoomNo);
                intent.putExtra("start time", floor2StartTime);
                intent.putExtra("end time", floor2EndTime);
                intent.putExtra("enroll or booked", floor2Enroll);
                startActivity(intent);
            }
        });

        wThirdFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference floor3 = wajedBuilding.child("Floor 3");
                int i = 0;
                for (String s : floor3RoomNo) {
                    RoomManagement roomManagement = new RoomManagement(floor3RoomNo[i], floor3StartTime[i], floor3EndTime[i], floor3Enroll[i]);
                    floor3.child(s).setValue(roomManagement);
                    i++;
                }
                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("room no", floor3RoomNo);
                intent.putExtra("start time", floor3StartTime);
                intent.putExtra("end time", floor3EndTime);
                intent.putExtra("enroll or booked", floor3Enroll);
                startActivity(intent);
            }
        });

        wForthFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference floor4 = wajedBuilding.child("Floor 4");
                int i = 0;
                for (String s : floor4RoomNo) {
                    RoomManagement roomManagement = new RoomManagement(floor4RoomNo[i], floor4StartTime[i], floor4EndTime[i], floor4Enroll[i]);
                    floor4.child(s).setValue(roomManagement);
                    i++;
                }
                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("room no", floor4RoomNo);
                intent.putExtra("start time", floor4StartTime);
                intent.putExtra("end time", floor4EndTime);
                intent.putExtra("enroll or booked", floor4Enroll);
                startActivity(intent);
            }
        });
        */


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
