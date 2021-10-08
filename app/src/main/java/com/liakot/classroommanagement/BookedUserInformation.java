package com.liakot.classroommanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookedUserInformation extends AppCompatActivity {

    Toolbar toolbar;
    Button enrollButton;
    EditText bookingCourseName, bookingCourseCode, bookingCourseTeacher, bookingStartTime, bookingEndTime;
    String roomRefSt;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String roomNo, crUniqueId, department, level, semester;
    TextView toolbarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_user_information);

        InitializeAll();

        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference roomRef = database.getReferenceFromUrl(roomRefSt);
                String courseName, courseCode, teacherName, startTime, endTime, roomStatus;

                courseName = bookingCourseName.getText().toString();
                courseCode = bookingCourseCode.getText().toString();
                teacherName = bookingCourseTeacher.getText().toString();
                startTime = bookingStartTime.getText().toString();
                endTime = bookingEndTime.getText().toString();
                roomStatus = "Booked";

                RoomActivityClass newRoom = new RoomActivityClass(roomNo, roomStatus, courseName, courseCode, teacherName, department, level, semester, crUniqueId, startTime, endTime);
                roomRef.setValue(newRoom).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "You booked this room", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private void InitializeAll() {

        //------for back button in menu bar-------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //---------initialization Section-----------
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        toolbarTextView = findViewById(R.id.toolbarTextView);

        enrollButton = findViewById(R.id.enrollButton);
        bookingCourseName = findViewById(R.id.bookingCurseName);
        bookingCourseCode = findViewById(R.id.bookingCourseCode);
        bookingCourseTeacher = findViewById(R.id.bookingCourseTeacher);
        bookingStartTime = findViewById(R.id.bookingStartTime);
        bookingEndTime = findViewById(R.id.bookingEndTime);

        roomRefSt = getIntent().getStringExtra("RoomRef");
        roomNo = getIntent().getStringExtra("RoomNo");

        toolbarTextView.setText("Room No: " + roomNo);

        crUniqueId = mAuth.getUid();
        DatabaseReference crProfileRef = database.getReference("Student").child("User").child(crUniqueId).child("Profile");

        crProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                AddUserInformation crInformation = snapshot.getValue(AddUserInformation.class);
                assert crInformation != null;
                level = crInformation.getUserLevel();
                semester = crInformation.getUserSemester();
                department = crInformation.getUserDepartment();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
