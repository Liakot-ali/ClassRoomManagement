package com.liakot.classroommanagement;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookedUserInformation extends AppCompatActivity {

    Toolbar toolbar;
    Button enrollButton;
    EditText bookingCourseName, bookingCourseCode, bookingCourseTeacher;
    ProgressBar progressBar;

    String roomRefSt;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String roomNo, crUniqueId, department, level, semester;
    TextView toolbarTextView, bookingStartTime, bookingEndTime;

    int firstHour, firstMinute, secondHour, secondMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_user_information);

        InitializeAll();

        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //-----------------To Hide the keyboard--------
                InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);


                DatabaseReference roomRef = database.getReferenceFromUrl(roomRefSt);
                String courseName, courseCode, teacherName, startTime, endTime, roomStatus;

                courseName = bookingCourseName.getText().toString();
                courseCode = bookingCourseCode.getText().toString();
                teacherName = bookingCourseTeacher.getText().toString();
                startTime = bookingStartTime.getText().toString();
                endTime = bookingEndTime.getText().toString();
                if (courseName.isEmpty()) {
                    bookingCourseName.setError("Enter course name");
                    bookingCourseName.requestFocus();
                    bookingCourseCode.clearFocus();
                    bookingCourseTeacher.clearFocus();
                    bookingStartTime.clearFocus();
                    bookingEndTime.clearFocus();
                } else if (courseCode.isEmpty()) {
                    bookingCourseCode.setError("Enter course code");
                    bookingCourseCode.requestFocus();
                    bookingCourseName.clearFocus();
                    bookingCourseTeacher.clearFocus();
                    bookingStartTime.clearFocus();
                    bookingEndTime.clearFocus();
                } else if (teacherName.isEmpty()) {
                    bookingCourseTeacher.setError("Enter course teacher name");
                    bookingCourseTeacher.requestFocus();
                    bookingCourseName.clearFocus();
                    bookingCourseCode.clearFocus();
                    bookingStartTime.clearFocus();
                    bookingEndTime.clearFocus();
                } else if (startTime.isEmpty()) {
                    bookingStartTime.setError("Enter class start time");
                    bookingStartTime.requestFocus();
                    bookingCourseName.clearFocus();
                    bookingCourseCode.clearFocus();
                    bookingCourseTeacher.clearFocus();
                    bookingEndTime.clearFocus();
                } else if (endTime.isEmpty()) {
                    bookingEndTime.setError("Enter class end time");
                    bookingEndTime.requestFocus();
                    bookingCourseName.clearFocus();
                    bookingCourseCode.clearFocus();
                    bookingCourseTeacher.clearFocus();
                    bookingStartTime.clearFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    String userUniqueId = mAuth.getUid();
                    assert userUniqueId != null;
                    final DatabaseReference userRoomRef = database.getReference("Student").child("User").child(userUniqueId).child("MyRoom");
                    roomStatus = "Booked";
                    final RoomActivityClass newRoom = new RoomActivityClass(roomNo, roomStatus, courseName, courseCode, teacherName, department, level, semester, crUniqueId, startTime, endTime);
                    roomRef.setValue(newRoom).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                userRoomRef.setValue(newRoom).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), "You booked this room", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(BookedUserInformation.this, MenuActivitySide.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
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

        progressBar = findViewById(R.id.progressBar5);

        toolbarTextView = findViewById(R.id.toolbarTextView);

        enrollButton = findViewById(R.id.enrollButton);
        bookingCourseName = findViewById(R.id.bookingCurseName);
        bookingCourseCode = findViewById(R.id.bookingCourseCode);
        bookingCourseTeacher = findViewById(R.id.bookingCourseTeacher);
        bookingStartTime = findViewById(R.id.bookingStartTime);
        bookingEndTime = findViewById(R.id.bookingEndTime);

        bookingStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog firstTime = new TimePickerDialog(BookedUserInformation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        firstHour = hourOfDay;
                        firstMinute = minute;

                        String time = firstHour + ":" + firstMinute;

                        SimpleDateFormat for24Hours = new SimpleDateFormat("hh:mm");
                        SimpleDateFormat for12Hours = new SimpleDateFormat("hh:mm a");

                        try {
                            Date newDate = for24Hours.parse(time);
                            assert newDate != null;
                            bookingStartTime.setText(for12Hours.format(newDate));

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false);

                firstTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                firstTime.updateTime(firstHour, firstMinute);
                firstTime.show();
            }
        });

        bookingEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog secondTime = new TimePickerDialog(BookedUserInformation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        secondHour = hourOfDay;
                        secondMinute = minute;

                        String time = secondHour + ":" + secondMinute;

                        SimpleDateFormat for24Hours = new SimpleDateFormat("hh:mm");
                        SimpleDateFormat for12Hours = new SimpleDateFormat("hh:mm a");
                        try {
                            Date newDate = for24Hours.parse(time);
                            assert newDate != null;
                            bookingEndTime.setText(for12Hours.format(newDate));

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false);

                secondTime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                secondTime.updateTime(secondHour, secondMinute);
                secondTime.show();
            }
        });


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
