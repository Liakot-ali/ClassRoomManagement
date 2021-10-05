package com.liakot.classroommanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BookedUserInformation extends AppCompatActivity {

    Toolbar toolbar;
    Button enrollButton;
    EditText bookingCourseName, bookingCourseCode, bookingCourseTeacher, bookingStartTime, bookingEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_user_information);
        InitializeAll();
    }

    private void InitializeAll() {

        enrollButton = findViewById(R.id.enrollButton);
        bookingCourseName = findViewById(R.id.bookingCurseName);
        bookingCourseCode = findViewById(R.id.bookingCourseCode);
        bookingCourseTeacher = findViewById(R.id.bookingCourseTeacher);
        bookingStartTime = findViewById(R.id.bookingStartTime);
        bookingEndTime = findViewById(R.id.bookingEndTime);


        //------for back button in menu bar-------
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
