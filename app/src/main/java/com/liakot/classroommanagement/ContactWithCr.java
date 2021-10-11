package com.liakot.classroommanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class ContactWithCr extends AppCompatActivity {

    Toolbar toolbar;
    String name, phone, studentId, email, session, picture;
    TextView crName, crPhone, crStudentId, crSession, crEmail;
    ImageView crPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_with_cr);

        InitializeAll();

    }

    private void InitializeAll() {

        //---------for back button in menu bar------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //------------Initialization section--------
        crName = findViewById(R.id.crContactName);
        crEmail = findViewById(R.id.crContactEmail);
        crPhone = findViewById(R.id.crContactPhone);
        crStudentId = findViewById(R.id.crContactStudentId);
        crPicture = findViewById(R.id.crContactPicture);
        crSession = findViewById(R.id.crContactSession);

        //--------get the value from previous activity--------------
        name = getIntent().getStringExtra("CrName");
        email = getIntent().getStringExtra("CrEmail");
        phone = getIntent().getStringExtra("CrPhone");
        studentId = getIntent().getStringExtra("CrSID");
        session = getIntent().getStringExtra("CrSession");
        picture = getIntent().getStringExtra("CrPicture");

        crName.setText("Name: " + name);
        crEmail.setText("Email: " + email);
        crPhone.setText("Phone: " + phone);
        crStudentId.setText("Student ID: " + studentId);
        crSession.setText("Session: " + session);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
