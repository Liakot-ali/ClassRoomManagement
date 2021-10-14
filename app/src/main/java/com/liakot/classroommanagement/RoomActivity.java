package com.liakot.classroommanagement;

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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RoomActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTextView;
    ListView roomListView;
    ProgressBar progressBar;

    String floorRefSt, toolbarText;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    ArrayList<RoomActivityClass> arrayList;
    BaseAdapter adapter;
    DatabaseReference floorRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        InitializeAll();

        assert floorRefSt != null;
        floorRef = FirebaseDatabase.getInstance().getReferenceFromUrl(floorRefSt);

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
        progressBar.setVisibility(View.GONE);
        roomListView.setAdapter(adapter);

        //-------For each item-------------
        roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                progressBar.setVisibility(View.VISIBLE);

                String roomStatus = arrayList.get(position).getRoomStatus();
                String roomRef = floorRefSt + "/RoomNo:" + arrayList.get(position).getRooNo();      //-------For every room reference----
                                                                                                   //-----AllBuildings/Building/floor/RoomNo:
                if(roomStatus.equals("Empty"))
                {
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(RoomActivity.this, BookedUserInformation.class);
                    intent.putExtra("RoomRef", roomRef);
                    intent.putExtra("RoomNo", arrayList.get(position).getRooNo());
                    startActivity(intent);
                }
                else{

                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(RoomActivity.this, Booked_Information.class);
                    intent.putExtra("RoomNo", arrayList.get(position).getRooNo());
                    intent.putExtra("CourseName", arrayList.get(position).getClassName());
                    intent.putExtra("CourseCode", arrayList.get(position).getCourseName());
                    intent.putExtra("TeacherName", arrayList.get(position).getTeacherName());
                    intent.putExtra("DepartmentName", arrayList.get(position).getDepartmentName());
                    intent.putExtra("Level",arrayList.get(position).getLevel());
                    intent.putExtra("Semester", arrayList.get(position).getSemester());
                    intent.putExtra("CrUniqueId", arrayList.get(position).getCrUniqueId());
                    intent.putExtra("StartTime", arrayList.get(position).getStartTime());
                    intent.putExtra("EndTime", arrayList.get(position).getEndTime());
                    startActivity(intent);

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

        progressBar = findViewById(R.id.progressBar4);
        progressBar.setVisibility(View.VISIBLE);

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
                    statusBtn.setText(statusSt);
                    startTime.setText("Start : 00:00");
                    endTime.setText("End : 00:00");
                }
                else{           //--------If status if 'booked'---------
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("hh:mm a");

                    String presentTime = format.format(date);

                    long currentTime = TimeInMills(presentTime);
                    long endTimeInMillis = TimeInMills(endTimeSt);

                    Log.e("Time", "getView: Current time" + currentTime);
                    Log.e("Time", "getView: End time" + endTimeInMillis);
                    Log.e("Time", "getView: Present Time in String" + presentTime);
                    Log.e("Time", "getView: End time in String" + endTimeSt);

                    if(endTimeInMillis>=currentTime)
                    {
                        statusBtn.setBackgroundResource(R.drawable.button_design_red);
                        statusBtn.setText(statusSt);
                        startTime.setText("Start : " + startTimeSt);
                        endTime.setText("End : " + endTimeSt);
                    }
                    else{
                        arrayList.get(position).setRoomStatus("Empty");
                        MakeEmpty(arrayList.get(position).getRooNo(), arrayList.get(position).getCrUniqueId());
                        statusBtn.setBackgroundResource(R.drawable.button_design1);
                        statusBtn.setText(arrayList.get(position).getRoomStatus());
                        startTime.setText("Start : 00:00");
                        endTime.setText("End : 00:00");
                    }
                }
                roomNo.setText("Room No: " + roomNoSt);

                return view;
            }
        };
    }

    //---------Make the room empty and remove form Cr MyRoom --------------
    private void MakeEmpty(String roomNo, String crUniqueId) {

        final DatabaseReference crRef = database.getReference("Student").child("User").child(crUniqueId).child("MyRoom").child(roomNo);
        RoomActivityClass newRoom = new RoomActivityClass(roomNo, "Empty", "", "", "", "", "", "", "", "", "");
        floorRef.child("RoomNo:" + roomNo).setValue(newRoom).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    crRef.removeValue();
                }
                else{
                    Toast.makeText(RoomActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
