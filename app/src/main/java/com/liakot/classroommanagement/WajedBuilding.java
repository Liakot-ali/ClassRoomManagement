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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WajedBuilding extends AppCompatActivity {

    Button wGroundFloor, wFirstFloor, wSecondFloor, wThirdFloor, wForthFloor;
    Toolbar toolbar;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wajed_building);
        InitializeAll();

        Button addValue = findViewById(R.id.addValueBtn);
        addValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WajedBuilding.this, AdminPageActivity.class);
                startActivity(intent);
            }
        });

        wGroundFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference floor0Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor0");
                String floorRef = floor0Ref.toString();

                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("FloorRef", floorRef);
                intent.putExtra("ToolbarText", "Ground Floor");
                startActivity(intent);
            }
        });
        wFirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference floor0Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor1");
                String floorRef = floor0Ref.toString();

                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("FloorRef", floorRef);
                intent.putExtra("ToolbarText", "First Floor");
                startActivity(intent);
            }
        });
        wSecondFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference floor0Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor2");
                String floorRef = floor0Ref.toString();

                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("FloorRef", floorRef);
                intent.putExtra("ToolbarText", "Second Floor");
                startActivity(intent);
            }
        });
        wThirdFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference floor0Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor3");
                String floorRef = floor0Ref.toString();

                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("FloorRef", floorRef);
                intent.putExtra("ToolbarText", "Third Floor");
                startActivity(intent);
            }
        });
        wForthFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference floor0Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor4");
                String floorRef = floor0Ref.toString();

                Intent intent = new Intent(WajedBuilding.this, RoomActivity.class);
                intent.putExtra("FloorRef", floorRef);
                intent.putExtra("ToolbarText", "Forth Floor");
                startActivity(intent);
            }
        });
    }

    private void InitializeAll() {

        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        wGroundFloor = findViewById(R.id.wGroundFloor);
        wFirstFloor = findViewById(R.id.wajedFirstFloor);
        wSecondFloor = findViewById(R.id.wajedSecondFloor);
        wThirdFloor = findViewById(R.id.wajedThirdFloor);
        wForthFloor = findViewById(R.id.wajedForthFloor);




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



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
