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


public class AcademicBuilding1 extends AppCompatActivity {
    Toolbar toolbar;

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
                Toast.makeText(AcademicBuilding1.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });

        academic1FirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding1.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });

        academic1SecondFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding1.this, "This is under construction", Toast.LENGTH_SHORT).show();
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
