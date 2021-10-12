package com.liakot.classroommanagement;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class UpdateProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int PICK_IMAGE = 100;

    Toolbar toolbar;
    Button addAnImageButton, updateButton;
    EditText updateUserName, updateUserEmail, updateUserPhoneNumber, updateUserSession;
    TextView userStudentId;
    Spinner updateDepartmentSpinner, updateLevelSpinner, updateSemesterSpinner;
    ImageView userProfileImage;
    Uri targetUri;
    ProgressBar progressBar;

    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth mAuth;

    String userName, userEmail, userPhoneNumber, userStudentID, userSession, userDepartment, userLevel, userSemester, userPassword, userPictureSt, userId;

    ArrayAdapter<CharSequence> departmentAdapter, levelAdapter, semesterAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        InitializeAll();
        SpinnerAll();


        final String  userUniqueId = mAuth.getUid();
        assert userUniqueId != null;
        final DatabaseReference profileRef = database.getReference("Student").child("User").child(userUniqueId).child("Profile");

        profileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final AddUserInformation userProfile = snapshot.getValue(AddUserInformation.class);
                assert userProfile != null;
                updateUserName.setText(userProfile.getUserName());
                updateUserEmail.setText(userProfile.getUserEmail());
                updateUserPhoneNumber.setText(userProfile.getUserPhoneNumber());
                updateUserSession.setText(userProfile.getUserSession());
                userStudentId.setText(userProfile.getUserSID());

                updateDepartmentSpinner.setSelection(departmentAdapter.getPosition(userProfile.getUserDepartment()));
                updateLevelSpinner.setSelection(levelAdapter.getPosition(userProfile.getUserLevel()));
                updateSemesterSpinner.setSelection(semesterAdapter.getPosition(userProfile.getUserSemester()));

                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        progressBar.setVisibility(View.VISIBLE);
                        //-----------------To Hide the keyboard--------
                        InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        methodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        userName = updateUserName.getText().toString();
                        userEmail = updateUserEmail.getText().toString();
                        userPhoneNumber = updateUserPhoneNumber.getText().toString();
                        userSession = updateUserSession.getText().toString();

                        userStudentID = userProfile.getUserSID();
                        userPassword = userProfile.getUserPass();
                        userId = userProfile.getUserUniqueId();
                        //TODO
                        userPictureSt = userProfile.getProfilePicture();

                        userDepartment = updateDepartmentSpinner.getSelectedItem().toString();
                        userLevel = updateLevelSpinner.getSelectedItem().toString();
                        userSemester = updateSemesterSpinner.getSelectedItem().toString();


                        AddUserInformation user = new AddUserInformation(userName, userEmail, userPhoneNumber, userStudentID, userDepartment, userLevel, userSemester, userSession, userPassword, userPictureSt, userUniqueId);
                        profileRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(UpdateProfile.this, "Your Profile is update", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UpdateProfile.this, MenuActivitySide.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                }
                                else{
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(UpdateProfile.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(UpdateProfile.this, "Check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void InitializeAll() {

        //------------for back button in toolbar-----------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //--------Initialization Section----------
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar3);

        updateUserName = findViewById(R.id.updateUserName);
        updateUserEmail = findViewById(R.id.updateUserEmail);
        updateUserPhoneNumber = findViewById(R.id.updateUserPhoneNumber);
        updateUserSession = findViewById(R.id.updateUserSession);
        userStudentId = findViewById(R.id.userStudentId);

        updateDepartmentSpinner = findViewById(R.id.updateDepartmentSpinner);
        updateLevelSpinner = findViewById(R.id.updateLevelSpinner);
        updateSemesterSpinner = findViewById(R.id.updateSemesterSpinner);

        updateButton = findViewById(R.id.updateButton);
        addAnImageButton = findViewById(R.id.addAnImageButton);
        userProfileImage = findViewById(R.id.userProfilePicture);



        addAnImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----------for add image------------
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });


    }

    private String getFileExtension(Uri imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    private void SpinnerAll() {

        departmentAdapter = ArrayAdapter.createFromResource(this, R.array.departmentSpinner2, android.R.layout.simple_list_item_activated_1);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateDepartmentSpinner.setAdapter(departmentAdapter);
        updateDepartmentSpinner.setOnItemSelectedListener(this);

        levelAdapter = ArrayAdapter.createFromResource(this, R.array.levelSpinner, android.R.layout.simple_list_item_activated_1);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateLevelSpinner.setAdapter(levelAdapter);
        updateLevelSpinner.setOnItemSelectedListener(this);

        semesterAdapter = ArrayAdapter.createFromResource(this, R.array.semesterSpinner, android.R.layout.simple_list_item_activated_1);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateSemesterSpinner.setAdapter(semesterAdapter);
        updateSemesterSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            targetUri = data.getData();
            Picasso.with(this).load(targetUri).into(userProfileImage);
//            userProfileImage.setImageURI(targetUri);
        }
    }

    //---------For back button in menu bar--------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
