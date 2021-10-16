package com.liakot.classroommanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MySpecificRoom extends AppCompatActivity {

    Toolbar toolbar;
    TextView roomNo, courseName, courseCode, teacherName, startTime, endTime;
    Button emptyRoomBtn;

    String roomNoSt, courseNameSt, courseCodeSt, teacherSt, startTimeSt, endTimeSt, roomRefSt, uniqueId;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_specific_room);

        InitializeAll();

        emptyRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO----make the room empty
                AlertDialog.Builder dialog = new AlertDialog.Builder(MySpecificRoom.this);
                dialog.setTitle("Are You Sure?");
                dialog.setMessage("Do you want to Empty the room?");
                dialog.setIcon(R.drawable.ic_delete);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference roomRef = database.getReferenceFromUrl(roomRefSt);
                        DatabaseReference myRoomRef = database.getReference("Student").child("User").child(uniqueId).child("MyRoom").child(roomNoSt);
                        myRoomRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    RoomActivityClass emptyRoom = new RoomActivityClass(roomNoSt, "Empty", "", "", "", "", "", "", "" , "", "", "");
                                    roomRef.setValue(emptyRoom).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(MySpecificRoom.this, "You remove the room", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MySpecificRoom.this, MyRoomActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(MySpecificRoom.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("No", null);
                dialog.show();
            }
        });
    }

    private void InitializeAll() {

        //------------Toolbar Back Button----------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        database = FirebaseDatabase.getInstance();

        //---------Initialization Section-------
        roomNo = findViewById(R.id.myRoomNo);
        courseName = findViewById(R.id.myRoomCourseName);
        courseCode = findViewById(R.id.myRoomCourseCode);
        teacherName = findViewById(R.id.myRoomCourseTeacher);
        startTime = findViewById(R.id.myRoomStartTime);
        endTime = findViewById(R.id.myRoomEndTime);
        emptyRoomBtn = findViewById(R.id.myRoomEmptyBtn);

        roomNoSt = getIntent().getStringExtra("RoomNo");
        courseNameSt = getIntent().getStringExtra("CourseName");
        courseCodeSt = getIntent().getStringExtra("CourseCode");
        teacherSt = getIntent().getStringExtra("TeacherName");
        startTimeSt = getIntent().getStringExtra("StartTime");
        endTimeSt = getIntent().getStringExtra("EndTime");
        roomRefSt = getIntent().getStringExtra("RoomRef");
        uniqueId = getIntent().getStringExtra("UniqueId");

        roomNo.setText("Room No : " + roomNoSt);
        courseName.setText("Course Name : " + courseNameSt);
        courseCode.setText("Course Code : " + courseCodeSt);
        teacherName.setText("teacher : " + teacherSt);
        startTime.setText("Start : " + startTimeSt);
        endTime.setText("End : " + endTimeSt);

    }

    //---------------------For Toolbar Back Button------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
