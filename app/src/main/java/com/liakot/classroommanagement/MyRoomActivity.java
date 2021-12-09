package com.liakot.classroommanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyRoomActivity extends AppCompatActivity {
    ListView myRoomList;
    Toolbar toolbar;
    TextView noRoom;

    BaseAdapter adapter;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    ArrayList<RoomActivityClass> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);

        InitializeAll();

        String userUniqueId = mAuth.getUid();
        assert userUniqueId != null;
        DatabaseReference myRoomRef = database.getReference("Student").child("User").child(userUniqueId).child("MyRoom");
        myRoomRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    RoomActivityClass newRoom = snapshot1.getValue(RoomActivityClass.class);
                    arrayList.add(newRoom);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyRoomActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        Adapter();
        myRoomList.setAdapter(adapter);


        myRoomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyRoomActivity.this, MySpecificRoom.class);

                intent.putExtra("RoomNo", arrayList.get(position).getRooNo());
                intent.putExtra("CourseName", arrayList.get(position).getClassName());
                intent.putExtra("CourseCode", arrayList.get(position).getCourseName());
                intent.putExtra("TeacherName", arrayList.get(position).getTeacherName());
                intent.putExtra("UniqueId", arrayList.get(position).getCrUniqueId());
                intent.putExtra("StartTime", arrayList.get(position).getStartTime());
                intent.putExtra("EndTime", arrayList.get(position).getEndTime());
                intent.putExtra("RoomRef", arrayList.get(position).getRoomRef());
                startActivity(intent);
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

        //------Initialization Section-----------
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        myRoomList = findViewById(R.id.myRoomListView);
        noRoom = findViewById(R.id.myRoomNoRoomTextView);
        arrayList = new ArrayList<>();

    }

    //---------Adapter All work------------
    public void Adapter()
    {
        adapter = new BaseAdapter() {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @Override
            public int getCount() {
                if(arrayList.size() == 0)
                {
                    //---------If no booked room-------
                    noRoom.setVisibility(View.VISIBLE);
                }
                else{
                    noRoom.setVisibility(View.GONE);
                }
                return arrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return arrayList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @SuppressLint("InflateParams")
            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null)
                {
                    view = inflater.inflate(R.layout.my_room_view, null);
                }

                TextView startTime, endTime, roomNo, courseName;
                startTime = view.findViewById(R.id.startTimeTextView);
                endTime = view.findViewById(R.id.endTimeTextView);
                roomNo = view.findViewById(R.id.roomNoTextView);
                courseName = view.findViewById(R.id.courseName);

                String startTimeSt, endTimeSt, roomNoSt, course;
                startTimeSt = arrayList.get(position).getStartTime();
                endTimeSt = arrayList.get(position).getEndTime();
                roomNoSt = arrayList.get(position).getRooNo();
                course = arrayList.get(position).getClassName();

                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("hh:mm a");

                String presentTime = format.format(date);

                long currentTime = TimeInMills(presentTime);
                long endTimeInMillis = TimeInMills(endTimeSt);

                if(endTimeInMillis <= currentTime)
                {
                    String userUniqueId = mAuth.getUid();
                    assert userUniqueId != null;

                    //--------if present time is greater than end time, then remove the room from MyRoom and Building booked room----------

                    DatabaseReference roomRef = database.getReferenceFromUrl(arrayList.get(position).getRoomRef());
                    DatabaseReference myRoomRef = database.getReference("Student").child("User").child(userUniqueId).child("MyRoom").child(arrayList.get(position).getRooNo());
                    myRoomRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                roomRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                            else{
                                Toast.makeText(MyRoomActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    startTime.setText("Start : " + startTimeSt);
                    endTime.setText("End : " + endTimeSt);
                    roomNo.setText("Room No : " + roomNoSt);
                    courseName.setText("Course : " + course);
                }
                return view;
            }
        };
    }

    public long TimeInMills(String time)
    {
        long endHour, endMinute;

        String[] time1 = time.split(":");
        endHour = Long.parseLong(time1[0]);
        String[] t1 = time1[1].split(" ");
        endMinute = Long.parseLong(t1[0]);

        if(t1[1].toLowerCase().equals("pm") && !time1[0].equals("12"))
        {
            endHour += 12;
        }
        return (endHour * 60 + endMinute) * 60000;
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
