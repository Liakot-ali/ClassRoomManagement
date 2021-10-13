package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Booked_Information extends AppCompatActivity {

    Toolbar toolbar;

    Button contactWithCrButton;
    TextView bookedDepartment, bookedLevel, bookedSemester, bookedCourseName, toolbarTextView;
    TextView bookedCourseCode, bookedCourseTeacher, bookedStartTime, bookedEndTime;
    ProgressBar progressBar;

    String roomNo, courseName, courseCode, teacherName, department, level, semester, startTime, endTime, crUniqueId;
    String crName, crPhone, crEmail, crStudentId, crSession, crProfilePicture, crLevel, crSemester, crDepartment, pictureVisibility;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked__information);

        InitializeAll();

        final DatabaseReference crProfileRef = database.getReference("Student").child("User").child(crUniqueId).child("Profile");

        crProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AddUserInformation crInformation = snapshot.getValue(AddUserInformation.class);

                assert crInformation != null;
                crName = crInformation.getUserName();
                crEmail = crInformation.getUserEmail();
                crPhone = crInformation.getUserPhoneNumber();
                crStudentId = crInformation.getUserSID();
                crProfilePicture = crInformation.getProfilePicture();
                crSession = crInformation.getUserSession();
                crLevel = crInformation.getUserLevel();
                crSemester = crInformation.getUserSemester();
                crDepartment = crInformation.getUserDepartment();
                pictureVisibility = crInformation.getPictureVisibility();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        bookedDepartment.setText(department);
        bookedLevel.setText("Level : " + level);
        bookedSemester.setText("Semester : " + semester);
        bookedStartTime.setText("Start : " + startTime);
        bookedEndTime.setText("End : " + endTime);
        bookedCourseName.setText(courseName);
        bookedCourseCode.setText(courseCode);
        bookedCourseTeacher.setText(teacherName);

        contactWithCrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(Booked_Information.this,ContactWithCr.class);
                intent.putExtra("CrName", crName);
                intent.putExtra("CrEmail", crEmail);
                intent.putExtra("CrPhone", crPhone);
                intent.putExtra("CrSID", crStudentId);
                intent.putExtra("CrSession", crSession);
                intent.putExtra("CrPicture", crProfilePicture);
                intent.putExtra("CrLevel", crLevel);
                intent.putExtra("CrSemester", crSemester);
                intent.putExtra("CrDepartment", crDepartment);
                intent.putExtra("Visibility", pictureVisibility);
                startActivity(intent);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void InitializeAll() {

        //--------for back button in menu bar---------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //----------Initialization Section----------
        database = FirebaseDatabase.getInstance();
        toolbarTextView = findViewById(R.id.toolbarTextView);

        contactWithCrButton = findViewById(R.id.contactWithCrButton);
        bookedDepartment = findViewById(R.id.bookedDepartment);
        bookedLevel = findViewById(R.id.bookedLevel);
        bookedSemester = findViewById(R.id.bookedSemester);
        bookedStartTime = findViewById(R.id.bookedStartTime);
        bookedEndTime = findViewById(R.id.bookedEndTime);
        bookedCourseName = findViewById(R.id.bookedCourseName);
        bookedCourseCode = findViewById(R.id.bookedCourseCode);
        bookedCourseTeacher = findViewById(R.id.bookedCourseTeacher);

        progressBar = findViewById(R.id.progressBar7);

        roomNo = getIntent().getStringExtra("RoomNo");
        courseName = getIntent().getStringExtra("CourseName");
        courseCode = getIntent().getStringExtra("CourseCode");
        teacherName = getIntent().getStringExtra("TeacherName");
        department = getIntent().getStringExtra("DepartmentName");
        level = getIntent().getStringExtra("Level");
        semester = getIntent().getStringExtra("Semester");
        startTime = getIntent().getStringExtra("StartTime");
        endTime = getIntent().getStringExtra("EndTime");
        crUniqueId = getIntent().getStringExtra("CrUniqueId");

        toolbarTextView.setText("Room No: " + roomNo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
