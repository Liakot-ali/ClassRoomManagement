package com.liakot.classroommanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CrContact extends AppCompatActivity {

    ListView crContactListView;
    Toolbar toolbar;
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
    }

    @SuppressLint("WrongViewCast")
    private void InitializeAll() {

        crContactListView = findViewById(R.id.crContactListView);
        MyAdapter adapter = new MyAdapter(this, crName, department, level, semester, contact);
        crContactListView.setAdapter(adapter);

        //------------Toolbar Back Button----------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    //------------------Create Custom Adapter----------
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String[] Name;
        String[] Department;
        String[] Level;
        String[] Semester;
        String[] Contact;

        MyAdapter(Context context1, String[] Name, String[] Department, String[] Level, String[] Semester, String[] Contact) {
            super(context1, R.layout.cr_contact_view, R.id.crName, Name);
            this.context = context1;
            this.Name = Name;
            this.Department = Department;
            this.Level = Level;
            this.Semester = Semester;
            this.Contact = Contact;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

            assert layoutInflater != null;
            @SuppressLint("ViewHolder") View crContactView = layoutInflater.inflate(R.layout.cr_contact_view, parent, false);

            TextView crName = crContactView.findViewById(R.id.crName);
            TextView crDepartment = crContactView.findViewById(R.id.crDepartment);
            TextView crLevel = crContactView.findViewById(R.id.crLevel);
            TextView crSemester = crContactView.findViewById(R.id.crSemester);
            TextView crContact = crContactView.findViewById(R.id.crContact);


            crName.setText("Name : "+Name[position]);
            crDepartment.setText("Department : "+Department[position]);
            crLevel.setText("Level : "+Level[position]);
            crSemester.setText("Semester : "+Semester[position]);
            crContact.setText("Contact : "+Contact[position]);
            return crContactView;
        }
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
