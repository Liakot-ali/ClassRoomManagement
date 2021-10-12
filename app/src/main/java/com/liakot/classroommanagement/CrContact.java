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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CrContact extends AppCompatActivity {

    ListView crContactListView;
    Toolbar toolbar;
    ArrayList<AddUserInformation> arrayList;

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    BaseAdapter adapter;
    DatabaseReference userRef;
    String[] crName = {"Mostakim Ara", "Shahariar Shishir", "Mostakim Ara", "Shahariar Shishir", "Mostakim Ara", "Shahariar Shishir", "Mostakim Ara", "Shahariar Shishir"};
    String[] department = {"aknflakd", "lafjirks", "abfkdj", "aknflakd", "lafjirks", "abfkdj", "aknflakd", "lafjirks"};
    String[] level = {"1", "2", "1", "2", "1", "2", "1", "2"};
    String[] semester = {"I", "II", "I", "II", "I", "II", "I", "II"};
    String[] contact = {"1234568", "1465484564", "1234568", "1465484564", "1234568", "1465484564", "1234568", "1465484564"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cr_contact);

        InitializeAll();

        userRef = database.getReference("Student").child("User");

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    AddUserInformation user = snapshot1.child("Profile").getValue(AddUserInformation.class);
                    arrayList.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Adapter();
//        MyAdapter adapter = new MyAdapter(this, crName, department, level, semester, contact);
        crContactListView.setAdapter(adapter);

        crContactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CrContact.this, ContactWithCr.class);

                intent.putExtra("CrName", arrayList.get(position).getUserName());
                intent.putExtra("CrEmail", arrayList.get(position).getUserEmail());
                intent.putExtra("CrPhone", arrayList.get(position).getUserPhoneNumber());
                intent.putExtra("CrSID", arrayList.get(position).getUserSID());
                intent.putExtra("CrSession", arrayList.get(position).getUserSession());
                intent.putExtra("CrPicture", arrayList.get(position).getProfilePicture());
                intent.putExtra("CrLevel", arrayList.get(position).getUserLevel());
                intent.putExtra("CrSemester", arrayList.get(position).getUserSemester());
                intent.putExtra("CrDepartment", arrayList.get(position).getUserDepartment());

                startActivity(intent);
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void InitializeAll() {

        //------------Toolbar Back Button----------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //------------Initialization Section--------
        crContactListView = findViewById(R.id.crContactListView);
        arrayList = new ArrayList<>();

    }


    //---------Adapter All work------------
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
                    view = inflater.inflate(R.layout.cr_contact_view, null);
                }

                TextView crName = view.findViewById(R.id.crName);
                TextView crDepartment = view.findViewById(R.id.crDepartment);
                TextView crLevel = view.findViewById(R.id.crLevel);
                TextView crSemester = view.findViewById(R.id.crSemester);
                TextView crContact = view.findViewById(R.id.crContact);

                crName.setText("Name : "+ arrayList.get(position).getUserName());
                crDepartment.setText("Department : " + arrayList.get(position).getUserDepartment());
                crLevel.setText("Level : " + arrayList.get(position).getUserLevel());
                crSemester.setText("Semester : " + arrayList.get(position).getUserSemester());
                crContact.setText("Contact : " + arrayList.get(position).getUserPhoneNumber());

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
