package com.liakot.classroommanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    Button registerUserSignInButton, resisterUserRegisterButton;
    Spinner departmentSpinner, levelSpinner, semesterSpinner;
    TextView registerUserName, registerUserEmail, registerUserStudentId, registerUserSession, registerUserPassword, registerUserPhoneNumber;
    ProgressBar progressBar;

    FirebaseDatabase database;
    FirebaseAuth mAuth;

    String userName, userEmail, userDepartment, userLevel, userSemester, userPhoneNumber, userSession, userStudentId, userPassword, userUniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitializeAll();
        SpinnerAll();

        registerUserSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        resisterUserRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                UserRegister();
            }
        });
    }

    public void InitializeAll() {

        toolbar = findViewById(R.id.toolbarDemo);
        progressBar = findViewById(R.id.progressBar);
        registerUserName = findViewById(R.id.registerUserName);
        registerUserEmail = findViewById(R.id.registerUserEmail);
        registerUserPhoneNumber = findViewById(R.id.registerUserPhoneNumber);
        registerUserStudentId = findViewById(R.id.registerUserStudentId);
        registerUserSession = findViewById(R.id.registerUserSession);
        registerUserPassword = findViewById(R.id.registerUserPassword);

        registerUserSignInButton = findViewById(R.id.registerUserSignInButton);
        resisterUserRegisterButton = findViewById(R.id.registerUserRegisterButton);

        departmentSpinner = findViewById(R.id.registerDepartmentSpinner);
        levelSpinner = findViewById(R.id.registerLevelSpinner);
        semesterSpinner = findViewById(R.id.registerSemesterSpinner);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


    }

    //----------------declare all Spinner--------------
    public void SpinnerAll() {

        //--------------------Department----------------
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departmentSpinner2, android.R.layout.simple_list_item_activated_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(adapter);
        departmentSpinner.setOnItemSelectedListener(this);

        //---------------------Level--------------------
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.levelSpinner, android.R.layout.simple_list_item_activated_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(adapter1);
        levelSpinner.setOnItemSelectedListener(this);

        //--------------------Semester--------------------------
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.semesterSpinner, android.R.layout.simple_list_item_activated_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(adapter2);
        semesterSpinner.setOnItemSelectedListener(this);

    }

    private void UserRegister() {

        final DatabaseReference userSidRef = database.getReference("Student").child("PhoneID");

        userEmail = registerUserEmail.getText().toString().trim();
        userPassword = registerUserPassword.getText().toString().trim();
        userName = registerUserName.getText().toString();
        userPhoneNumber = registerUserPhoneNumber.getText().toString();
        userSession = registerUserSession.getText().toString();
        userStudentId = registerUserStudentId.getText().toString();

        //------------access selected spinner item---------
        userDepartment = departmentSpinner.getSelectedItem().toString();
        userLevel = levelSpinner.getSelectedItem().toString();
        userSemester = semesterSpinner.getSelectedItem().toString();


        if (userName.isEmpty())
        {
            registerUserName.setError("This field is empty");
            registerUserName.requestFocus();
        }
        if (userEmail.isEmpty()) {
            registerUserEmail.setError("Enter an email address");
            registerUserEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            registerUserEmail.setError("Enter a valid email");
            registerUserEmail.requestFocus();
            return;
        }
        if (userPhoneNumber.isEmpty()) {
            registerUserPhoneNumber.setError("Enter your phone number");
            registerUserPhoneNumber.requestFocus();
            return;
        }
        if (userPhoneNumber.length() != 11) {
            registerUserPhoneNumber.setError("Enter a valid phone number");
            registerUserPhoneNumber.requestFocus();
            return;
        }
        if (userStudentId.isEmpty()) {
            registerUserStudentId.setError("Enter your Student ID");
            registerUserStudentId.requestFocus();
            return;
        }

        if (userStudentId.length() != 7) {
            registerUserStudentId.setError("Enter valid Student ID");
            registerUserStudentId.requestFocus();
            return;
        }
        if (userSession.isEmpty()) {
            registerUserSession.setError("Please fill this field with your Academic session");
            registerUserSession.requestFocus();
            return;
        }
        if (userPassword.isEmpty()) {
            registerUserPassword.setError("Enter a password");
            registerUserPassword.requestFocus();
            return;
        }
        if (userPassword.length() <= 6) {
            registerUserPassword.setError("Password should contain more then 6 digits");
            registerUserPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        userSidRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("Phone").child(userPhoneNumber).exists())
                {
                    registerUserPhoneNumber.setError("This Phone number is already registered");
                    registerUserPhoneNumber.requestFocus();
                    progressBar.setVisibility(View.GONE);
                }
                else if (snapshot.child("SID").child(userStudentId).exists()) {
                    registerUserStudentId.setError("This Student ID is already registered");
                    registerUserStudentId.requestFocus();
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        userUniqueId = mAuth.getUid();

                                        DatabaseReference profileRef = database.getReference("Student").child("User").child(userUniqueId).child("Profile");

                                        //----------------store all information into database----------
                                        userSidRef.child("SID").child(userStudentId).setValue(userStudentId);
                                        userSidRef.child("Phone").child(userPhoneNumber).setValue(userPhoneNumber);
                                        AddUserInformation userInformation = new AddUserInformation(userName, userEmail, userPhoneNumber, userStudentId, userDepartment, userLevel, userSemester, userSession, userPassword, "", userUniqueId);
                                        profileRef.setValue(userInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progressBar.setVisibility(View.GONE);
                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(getApplicationContext(), "Register is Successful", Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(RegisterActivity.this, MenuActivitySide.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                    registerUserName.setText("");
                                                    registerUserPhoneNumber.setText("");
                                                    registerUserSession.setText("");
                                                    registerUserStudentId.setText("");
                                                    registerUserEmail.setText("");
                                                    registerUserPassword.setText("");
                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                                                }
                                            }
;                                        });
                                    } else {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
