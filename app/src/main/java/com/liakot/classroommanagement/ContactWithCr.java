package com.liakot.classroommanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactWithCr extends AppCompatActivity {

    Toolbar toolbar;
    String name, phone, studentId, email, session, picture, department, level, semester, visibility;
    TextView crName, crPhone, crStudentId, crSession, crEmail, crDepartment, crLevel, crSemester;
    CircleImageView crPicture;

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
        crDepartment = findViewById(R.id.crContactDepartment);
        crLevel = findViewById(R.id.crContactLevel);
        crSemester = findViewById(R.id.crContactSemester);

        //--------get the value from previous activity--------------
        name = getIntent().getStringExtra("CrName");
        email = getIntent().getStringExtra("CrEmail");
        phone = getIntent().getStringExtra("CrPhone");
        studentId = getIntent().getStringExtra("CrSID");
        session = getIntent().getStringExtra("CrSession");
        picture = getIntent().getStringExtra("CrPicture");
        department = getIntent().getStringExtra("CrDepartment");
        level = getIntent().getStringExtra("CrLevel");
        semester = getIntent().getStringExtra("CrSemester");
        visibility = getIntent().getStringExtra("Visibility");

        if (!picture.isEmpty() && visibility.equals("true"))
        {
            Picasso.get().load(picture).into(crPicture);
        }
        crName.setText("Name : " + name);
        crEmail.setText("Email : " + email);
        crDepartment.setText("Department : " + department);
        crLevel.setText("Level : " + level);
        crSemester.setText("Semester : " + semester);
        crStudentId.setText("Student ID : " + studentId);
        crPhone.setText("Phone : " + phone);
        crSession.setText("Session : " + session);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
