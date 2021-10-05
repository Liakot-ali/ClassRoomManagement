package com.liakot.classroommanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AcademicBuilding4 extends AppCompatActivity {

    Toolbar toolbar;
    Button floor0, floor1, floor2, floor3, floor4, floor5, floor6, floor7, floor8, floor9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_building4);
        InitializeAll();
    }

    private void InitializeAll() {

        floor0 = findViewById(R.id.academic4GroundFloor);
        floor1 = findViewById(R.id.academic4FirstFloor);
        floor2 = findViewById(R.id.academic4SecondFloor);
        floor3 = findViewById(R.id.academic4ThirdFloor);
        floor4 = findViewById(R.id.academic4ForthFloor);
        floor5 = findViewById(R.id.academic4FifthFloor);
        floor6 = findViewById(R.id.academic4SixthFloor);
        floor7 = findViewById(R.id.academic4SeventhFloor);
        floor8 = findViewById(R.id.academic4EighthFloor);
        floor9 = findViewById(R.id.academic4NinthFloor);

        floor0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        floor9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "This floor is under construction", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //-------------for back button in menu bar---------
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
