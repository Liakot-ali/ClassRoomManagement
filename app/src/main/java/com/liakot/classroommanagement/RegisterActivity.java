package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    Button registerUserSignInButton, resisterUserRegisterButton;
    Spinner departmentSpinner, levelSpinner, semesterSpinner;
    TextView registerUserName, registerUserEmail, registerUserStudentId, registerUserSession, registerUserPassword;
    TextView registerUserPhoneNumber;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    DatabaseReference User;
    DatabaseReference userSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitializeAll();
        SpinnerAll();
    }

    public void InitializeAll() {

        //-----------For storing user Information-------------
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User Information");

        //------------------For User Profile Activity-----------------
        User = FirebaseDatabase.getInstance().getReference("User Profile");

        userSid = FirebaseDatabase.getInstance().getReference("User ID and Phone");

        progressBar = findViewById(R.id.progressBar);
        registerUserName = findViewById(R.id.registerUserName);
        registerUserEmail = findViewById(R.id.registerUserEmail);
        registerUserPhoneNumber = findViewById(R.id.registerUserPhoneNumber);
        registerUserStudentId = findViewById(R.id.registerUserStudentId);
        registerUserSession = findViewById(R.id.registerUserSession);
        registerUserPassword = findViewById(R.id.registerUserPassword);
//        registerInvalidChecker = findViewById(R.id.registerInvalidChecker);

        registerUserSignInButton = findViewById(R.id.registerUserSignInButton);
        resisterUserRegisterButton = findViewById(R.id.registerUserRegisterButton);
        registerUserSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        resisterUserRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserRegister();
            }
        });
    }

    private void UserRegister() {

        final String userEmail, userPass;

        final String userName, userPhoneNumber, userSession, userStudentId;

        userEmail = registerUserEmail.getText().toString().trim();
        userPass = registerUserPassword.getText().toString().trim();
        userName = registerUserName.getText().toString();
        userPhoneNumber = registerUserPhoneNumber.getText().toString();
        userSession = registerUserSession.getText().toString();
        userStudentId = registerUserStudentId.getText().toString();

        if (userEmail.isEmpty()) {
            registerUserEmail.setError("Please enter an email address");
            registerUserEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            registerUserEmail.setError("Enter a valid email");
            registerUserEmail.requestFocus();
            return;
        }
        if (userPass.isEmpty()) {
            registerUserPassword.setError("Please enter a password");
            registerUserPassword.requestFocus();
            return;
        }
        if (userPass.length() <= 6) {
            registerUserPassword.setError("Password should contain more then 6 digits");
            registerUserPassword.requestFocus();
            return;
        }
        if (userName.isEmpty()) {
            registerUserName.setError("Please fill this field with your Name");
            registerUserName.requestFocus();
            return;
        }
        if (userPhoneNumber.isEmpty()) {
            registerUserPhoneNumber.setError("Please fill this field with your Phone number");
            registerUserPhoneNumber.requestFocus();
            return;
        }
        if (userPhoneNumber.length() != 11) {
            registerUserPhoneNumber.setError("Enter a valid phone number");
            registerUserPhoneNumber.requestFocus();
            return;
        }
        if (userStudentId.isEmpty()) {
            registerUserStudentId.setError("Please fill this field with your Student ID");
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
        progressBar.setVisibility(View.VISIBLE);

        userSid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("User Phone Number").child(userPhoneNumber).exists())
                {
                    registerUserPhoneNumber.setError("This Phone number is already registered");
                    registerUserPhoneNumber.requestFocus();
                    progressBar.setVisibility(View.GONE);
                }
                else if (snapshot.child("User SID").child(userStudentId).exists()) {
                    registerUserStudentId.setError("This Student ID is already registered");
                    registerUserStudentId.requestFocus();
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(userEmail, userPass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        finish();

                                        //----------access all user information in String
                                        String userName, userPhoneNumber, userSID, userDepartment, userLevel, userSemester, userSession;
                                        userName = registerUserName.getText().toString();
                                        userPhoneNumber = registerUserPhoneNumber.getText().toString();
                                        userSID = registerUserStudentId.getText().toString();
                                        userSession = registerUserSession.getText().toString();


                                        //------------access selected spinner item---------
                                        userDepartment = departmentSpinner.getSelectedItem().toString();
                                        userLevel = levelSpinner.getSelectedItem().toString();
                                        userSemester = semesterSpinner.getSelectedItem().toString();

                                        DatabaseReference departmentReference = databaseReference.child(userDepartment);


                                        //----------------store all information into database----------
                                        userSid.child("User SID").child(userStudentId).setValue(userStudentId);
                                        userSid.child("User Phone Number").child(userPhoneNumber).setValue(userPhoneNumber);
                                        AddUserInformation userInformation = new AddUserInformation(userName, userEmail, userPhoneNumber, userSID, userLevel, userSemester, userSession, userPass);
                                        departmentReference.child(userSID).setValue(userInformation);

                                        AddUserProfile userProfile = new AddUserProfile(userName, userEmail, userPhoneNumber, userSID, userDepartment, userLevel, userSemester, userSession, userPass);
                                        String userId = userEmail.substring(0, userEmail.indexOf("@")) + userPass;

                                        User.child(userId).setValue(userProfile);

                                        Toast.makeText(getApplicationContext(), "Register is Successful", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, MenuActivitySide.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.putExtra("Email", userEmail);
                                        intent.putExtra("Password", userPass);
                                        intent.putExtra("Name",userName);
                                        startActivity(intent);
                                        registerUserEmail.setText("");
                                        registerUserPassword.setText("");
                                    } else {
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //----------------declare all Spinner--------------
    public void SpinnerAll() {
        departmentSpinner = findViewById(R.id.registerDepartmentSpinner);
        levelSpinner = findViewById(R.id.registerLevelSpinner);
        semesterSpinner = findViewById(R.id.registerSemesterSpinner);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
