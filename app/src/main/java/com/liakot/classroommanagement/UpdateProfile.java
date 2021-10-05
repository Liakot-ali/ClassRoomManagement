package com.liakot.classroommanagement;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

    Toolbar toolbar;
    Button addAnImageButton;
    Button updateButton;
    EditText updateUserName, updateUserEmail, updateUserPhoneNumber, updateUserSession, updateOldPassword, updateNewPassword, updateConfirmPassword;
    TextView userStudentId;
    Spinner updateDepartmentSpinner, updateLevelSpinner, updateSemesterSpinner;
    ImageView userProfileImage;
    Uri targetUri;
    ProgressBar progressBar;

    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    DatabaseReference userProfileData;
    int userId = 1802035;
    String userEmail, userPass;

    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        databaseReference = FirebaseDatabase.getInstance().getReference("User Information");
        storageReference = FirebaseStorage.getInstance().getReference("User Information");
        mAuth = FirebaseAuth.getInstance();
        userProfileData = FirebaseDatabase.getInstance().getReference("User Profile");

        progressBar = findViewById(R.id.progressBar3);
        updateUserName = findViewById(R.id.updateUserName);
        updateUserEmail = findViewById(R.id.updateUserEmail);
        updateUserPhoneNumber = findViewById(R.id.updateUserPhoneNumber);
        updateUserSession = findViewById(R.id.updateUserSession);
        updateOldPassword = findViewById(R.id.updateOldPassword);
        updateNewPassword = findViewById(R.id.updateNewPassword);
        updateConfirmPassword = findViewById(R.id.updateConfirmNewPassword);
        userStudentId = findViewById(R.id.userStudentId);

        updateDepartmentSpinner = findViewById(R.id.updateDepartmentSpinner);
        updateLevelSpinner = findViewById(R.id.updateLevelSpinner);
        updateSemesterSpinner = findViewById(R.id.updateSemesterSpinner);

        updateButton = findViewById(R.id.updateButton);
        addAnImageButton = findViewById(R.id.addAnImageButton);
        userProfileImage = findViewById(R.id.userProfilePicture);
        userEmail = getIntent().getStringExtra("Email");
        userPass = getIntent().getStringExtra("Password");
        SetValue();
        InitializeAll();
        SpinnerAll();
    }

    private void SetValue() {

        userProfileData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String userImage;

                String userId = userEmail.substring(0, userEmail.indexOf("@")) + userPass;

                AddUserProfile userProfile;
                userProfile = snapshot.child(userId).getValue(AddUserProfile.class);
                userImage = snapshot.child(userId).child("userProfilePicture").getValue(String.class);

//                Uri imageUri;
//                imageUri=snapshot.child(userId).child("userProfilePicture").getValue();
                assert userProfile != null;

                updateUserName.setText(userProfile.getUserName());
                updateUserEmail.setText(userProfile.getUserEmail());
                updateUserPhoneNumber.setText(userProfile.getUserPhoneNumber());
                updateUserSession.setText(userProfile.getUserSession());
                userStudentId.setText(userProfile.getUserSID());
                updateOldPassword.setText(userProfile.getUserPass());

                //TODO not tested yet
                Picasso.with(getApplicationContext()).load(userImage).fit().centerCrop().into(userProfileImage);

//                updateDepartmentSpinner.setSelection(updateDepartmentSpinner.getLastVisiblePosition());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void InitializeAll() {

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

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName;
                final String userEmail, userPhoneNumber, userSession, userOldPass, userNewPass, userNewPassConfirm, userDepartment, userLevel, userSemester;

                final String userStudentID;
                userName = updateUserName.getText().toString();
                userEmail = updateUserEmail.getText().toString();
                userStudentID = userStudentId.getText().toString();
                userPhoneNumber = updateUserPhoneNumber.getText().toString();
                userSession = updateUserSession.getText().toString();
                userOldPass = updateOldPassword.getText().toString();
                userNewPass = updateNewPassword.getText().toString();
                userNewPassConfirm = updateConfirmPassword.getText().toString();

                userDepartment = updateDepartmentSpinner.getSelectedItem().toString();
                userLevel = updateLevelSpinner.getSelectedItem().toString();
                userSemester = updateSemesterSpinner.getSelectedItem().toString();

                final DatabaseReference departmentRef = databaseReference.child(userDepartment);

                StorageReference sRef = storageReference.child(userStudentID + "." + getFileExtension(targetUri));
                progressBar.setVisibility(View.VISIBLE);

                sRef.putFile(targetUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressBar.setVisibility(View.GONE);

                                AddUserInformation userInformation = new AddUserInformation(userName, userEmail, userPhoneNumber, userStudentID, userLevel, userSemester, userSession, userNewPass);
                                departmentRef.child(userStudentID).setValue(userInformation);
                                departmentRef.child(userStudentID).child("userProfilePicture").setValue(taskSnapshot.getStorage().getDownloadUrl().toString());

                                AddUserProfile userProfile = new AddUserProfile(userName, userEmail, userPhoneNumber, userStudentID, userDepartment, userLevel, userSemester, userSession, userNewPass);
                                String userId = userEmail.substring(0, userEmail.indexOf("@")) + userNewPass;
                                userProfileData.child(userId).setValue(userProfile);

                                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                                while (!uriTask.isSuccessful()) ;

                                Uri downloadUrl = uriTask.getResult();

                                assert downloadUrl != null;
                                userProfileData.child(userId).child("userProfilePicture").setValue(downloadUrl.toString());


                                Toast.makeText(getApplicationContext(), "Image is stored Successfully", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
//                databaseReference.child(String.valueOf(userId)).setValue(userName);

//                Toast.makeText(getApplicationContext(), "Your name is added", Toast.LENGTH_LONG).show();
//                finish();
//                Intent intent = new Intent(UpdateProfile.this, MenuActivitySide.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
            }
        });

        //------------for back button in toolbar-----------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private String getFileExtension(Uri imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    private void SpinnerAll() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departmentSpinner2, android.R.layout.simple_list_item_activated_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateDepartmentSpinner.setAdapter(adapter);
        updateDepartmentSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.levelSpinner, android.R.layout.simple_list_item_activated_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateLevelSpinner.setAdapter(adapter1);
        updateLevelSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.semesterSpinner, android.R.layout.simple_list_item_activated_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateSemesterSpinner.setAdapter(adapter2);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
