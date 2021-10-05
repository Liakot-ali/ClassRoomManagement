package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Booked_Information extends AppCompatActivity {

    Toolbar toolbar;

    Button contactWithCrButton;
    TextView bookedDepartment, bookedLevel, bookedSemester, bookedCourseName;
    TextView bookedCourseCode, bookedCourseTeacher, bookedStartTime, bookedEndTime;

    String department = "Computer Science & Engineering";
    String level = "2";
    String semester = "II";
    String courseName = "OOP with Java";
    String courseCode = "CSE 251";
    String courseTeacher = "Md Abu Marjan";
    String startTime = "10:00";
    String endTime = "12:50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked__information);

        InitializeAll();
    }

    private void InitializeAll() {

        contactWithCrButton = findViewById(R.id.contactWithCrButton);

        bookedDepartment = findViewById(R.id.bookedDepartment);
        bookedLevel = findViewById(R.id.bookedLevel);
        bookedSemester = findViewById(R.id.bookedSemester);
        bookedStartTime = findViewById(R.id.bookedStartTime);
        bookedEndTime = findViewById(R.id.bookedEndTime);
        bookedCourseName = findViewById(R.id.bookedCourseName);
        bookedCourseCode = findViewById(R.id.bookedCourseCode);
        bookedCourseTeacher = findViewById(R.id.bookedCourseTeacher);

        bookedDepartment.setText("Department : "+department);
        bookedLevel.setText("Level : "+level);
        bookedSemester.setText("Semester : "+semester);
        bookedStartTime.setText("Start : "+startTime);
        bookedEndTime.setText("End : "+endTime);
        bookedCourseName.setText("Course Name : "+courseName);
        bookedCourseCode.setText("Course Code : "+courseCode);
        bookedCourseTeacher.setText("Teacher : "+courseTeacher);

        contactWithCrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booked_Information.this,ContactWithCr.class);
                startActivity(intent);
            }
        });
        //--------for back button in menu bar---------
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
