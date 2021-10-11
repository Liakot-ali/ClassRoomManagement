package com.liakot.classroommanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminPageActivity extends AppCompatActivity {

    Button floor0, floor1, floor2, floor3, floor4;

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference floor0Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor0");
        final DatabaseReference floor1Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor1");
        final DatabaseReference floor2Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor2");
        final DatabaseReference floor3Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor3");
        final DatabaseReference floor4Ref = database.getReference("AllBuildings").child("WajedBuilding").child("Floor4");

        floor0 = findViewById(R.id.floor0Btn);
        floor1 = findViewById(R.id.floor1Btn);
        floor2 = findViewById(R.id.floor2Btn);
        floor3 = findViewById(R.id.floor3Btn);
        floor4 = findViewById(R.id.floor4Btn);

        floor0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=101; i<150; i++)
                {
                    String roomNo = String.valueOf(i);

                    RoomActivityClass newRoom = new RoomActivityClass(roomNo, "Empty", "", "", "", "", "", "", "", "", "");
                    floor0Ref.child("RoomNo:" + roomNo).setValue(newRoom);
                }
                Toast.makeText(getApplicationContext(), "Floor0 Value Added",Toast.LENGTH_SHORT).show();
            }
        });
        floor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 201; i <= 250; i++)
                {
                    String roomNo = String.valueOf(i);

                    RoomActivityClass newRoom = new RoomActivityClass(roomNo, "Empty", "", "", "", "", "","", "", "", "");
                    floor1Ref.child("RoomNo:" + roomNo).setValue(newRoom);
                }
                Toast.makeText(getApplicationContext(), "Floor1 Value Added",Toast.LENGTH_SHORT).show();
            }
        });
        floor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=301; i<=350; i++)
                {
                    String roomNo = String.valueOf(i);

                    RoomActivityClass newRoom = new RoomActivityClass(roomNo, "Empty", "", "", "", "", "", "", "","", "");
                    floor2Ref.child("RoomNo:" + roomNo).setValue(newRoom);
                }
                Toast.makeText(getApplicationContext(), "Floor2 Value Added",Toast.LENGTH_SHORT).show();
            }
        });

        floor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=401; i<=450; i++)
                {
                    String roomNo = String.valueOf(i);

                    RoomActivityClass newRoom = new RoomActivityClass(roomNo, "Empty", "", "", "", "", "", "","", "", "");
                    floor3Ref.child("RoomNo:" + roomNo).setValue(newRoom);
                }
                Toast.makeText(getApplicationContext(), "Floor3 Value Added",Toast.LENGTH_SHORT).show();
            }
        });
        floor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=501; i<550; i++)
                {
                    String roomNo = String.valueOf(i);

                    RoomActivityClass newRoom = new RoomActivityClass(roomNo, "Empty", "", "", "", "", "", "","", "", "");
                    floor4Ref.child("RoomNo:" + roomNo).setValue(newRoom);
                }
                Toast.makeText(getApplicationContext(), "Floor4 Value Added",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
