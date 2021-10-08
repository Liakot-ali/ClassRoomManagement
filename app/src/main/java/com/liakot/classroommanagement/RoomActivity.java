package com.liakot.classroommanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTextView;
    ListView roomListView;

    String floorRefSt, toolbarText;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    ArrayList<RoomActivityClass> arrayList;
    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        InitializeAll();

        assert floorRefSt != null;
        final DatabaseReference floorRef = FirebaseDatabase.getInstance().getReferenceFromUrl(floorRefSt);

        floorRef.addListenerForSingleValueEvent(new ValueEventListener() {
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Adapter();
        roomListView.setAdapter(adapter);

        //-------For each item-------------
        roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String roomStatus = arrayList.get(position).getRoomStatus();
                String roomRef = floorRefSt + "/RoomNo:" + arrayList.get(position).getRooNo();      //-------For every room reference----
                                                                                                   //-----AllBuildings/Building/floor/RoomNo:
                if(roomStatus.equals("Empty"))
                {
                    Intent intent = new Intent(RoomActivity.this, BookedUserInformation.class);
                    intent.putExtra("RoomRef", roomRef);
                    intent.putExtra("RoomNo", arrayList.get(position).getRooNo());
                    startActivity(intent);
                }
                else{
                    DatabaseReference room = FirebaseDatabase.getInstance().getReferenceFromUrl(roomRef);
                    room.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String roomNo, className, courseCode, teacherName, departmentName, level, semester, crUniqueId, startTime, endTime;
                            RoomActivityClass newRoom = snapshot.getValue(RoomActivityClass.class);
                            assert newRoom != null;
                            roomNo = newRoom.getRooNo();
                            className = newRoom.getClassName();
                            courseCode = newRoom.getCourseName();
                            teacherName = newRoom.getTeacherName();
                            departmentName = newRoom.getDepartmentName();
                            level = newRoom.getLevel();
                            semester = newRoom.getSemester();
                            crUniqueId = newRoom.getCrUniqueId();
                            startTime = newRoom.getStartTime();
                            endTime = newRoom.getEndTime();

                            Intent intent = new Intent(RoomActivity.this, Booked_Information.class);
                            intent.putExtra("RoomNo", roomNo);
                            intent.putExtra("CourseName", className);
                            intent.putExtra("CourseCode", courseCode);
                            intent.putExtra("TeacherName", teacherName);
                            intent.putExtra("DepartmentName", departmentName);
                            intent.putExtra("Level",level);
                            intent.putExtra("Semester", semester);
                            intent.putExtra("CrUniqueId", crUniqueId);
                            intent.putExtra("StartTime", startTime);
                            intent.putExtra("EndTime", endTime);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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
        mAuth = FirebaseAuth.getInstance();
        arrayList = new ArrayList<>();

        toolbarTextView = findViewById(R.id.toolbarTextView);
        roomListView = findViewById(R.id.roomListView);

        //------receive the data from parent activity----------
        floorRefSt = getIntent().getStringExtra("FloorRef");
        toolbarText = getIntent().getStringExtra("ToolbarText");

        toolbarTextView.setText(toolbarText);
    }

    //------------Adapter all work----------
    public void Adapter()
    {
        adapter = new BaseAdapter() {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @Override
            public int getCount() {
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
                    view = inflater.inflate(R.layout.activity_view, null);
                }

                TextView startTime, endTime, roomNo;
                Button statusBtn;
                startTime = view.findViewById(R.id.startTimeTextView);
                endTime = view.findViewById(R.id.endTimeTextView);
                roomNo = view.findViewById(R.id.roomNoTextView);
                statusBtn = view.findViewById(R.id.enrollButton);

                String startTimeSt, endTimeSt, roomNoSt, statusSt;
                startTimeSt = arrayList.get(position).getStartTime();
                endTimeSt = arrayList.get(position).getEndTime();
                roomNoSt = arrayList.get(position).getRooNo();
                statusSt = arrayList.get(position).getRoomStatus();

                if(statusSt.equals("Empty"))
                {
                    statusBtn.setBackgroundResource(R.drawable.button_design1);
                    statusBtn.setText("Empty");
                    startTime.setText("Start : 00:00");
                    endTime.setText("End : 00:00");
                }
                else{
                    statusBtn.setBackgroundResource(R.drawable.button_design_red);
                    statusBtn.setText("Booked");
                    startTime.setText("Start : " + startTimeSt);
                    endTime.setText("End : " + endTimeSt);
                }
                roomNo.setText("Room No: " + roomNoSt);

                return view;
            }
        };
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
