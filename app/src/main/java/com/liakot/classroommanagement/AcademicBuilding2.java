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

public class AcademicBuilding2 extends AppCompatActivity {

    Button academic2GroundFloor, academic2FirstFloor, academic2SecondFloor;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_building2);

        InitializeAll();
    }

    private void InitializeAll() {

        //----------For back Button in toolbar------------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //--------Initialization Section-------
        academic2GroundFloor = findViewById(R.id.academic2GroundFloor);
        academic2FirstFloor = findViewById(R.id.academic2FirstFloor);
        academic2SecondFloor = findViewById(R.id.academic2SecondFloor);

        academic2GroundFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding2.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });

        academic2FirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding2.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });

        academic2SecondFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AcademicBuilding2.this, "This is under construction", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
