package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileFragment extends Fragment {

    ProgressBar progressBar;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    private String userEmailMain, userPassMain;
    private String name, email, department, level, semester, session, sID, phoneNumber, userUniqueId, profilePictureSt;
    Button updateProfileButton;
    private TextView userName, userEmail, userDepartment, userLevel, userSemester, userSession, userPhoneNumber, userSID;
    private ImageView userProfilePicture;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_profile, container, false);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        updateProfileButton = view.findViewById(R.id.updateProfileButton);
        progressBar = view.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);

        userProfilePicture = view.findViewById(R.id.userProfilePicture);
        userName = view.findViewById(R.id.userName);
        userEmail = view.findViewById(R.id.userEmail);
        userDepartment = view.findViewById(R.id.userDepartment);
        userLevel = view.findViewById(R.id.userLevel);
        userSemester = view.findViewById(R.id.userSemester);
        userSID = view.findViewById(R.id.userSID);
        userPhoneNumber = view.findViewById(R.id.userPhoneNumber);
        userSession = view.findViewById(R.id.userSession);


        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        userUniqueId = mAuth.getUid();
        DatabaseReference profileRef = database.getReference("Student").child("User").child(userUniqueId).child("Profile");
        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);

                AddUserInformation userProfile = snapshot.getValue(AddUserInformation.class);

                assert userProfile != null;
                name = userProfile.getUserName();
                email = userProfile.getUserEmail();
                department = userProfile.getUserDepartment();
                level = userProfile.getUserLevel();
                semester = userProfile.getUserSemester();
                sID = userProfile.getUserSID();
                phoneNumber = userProfile.getUserPhoneNumber();
                session = userProfile.getUserSession();
                profilePictureSt = userProfile.getProfilePicture();

                if(!profilePictureSt.isEmpty())
                {
                    //TODO
                    Toast.makeText(getActivity(), "Profile picture found", Toast.LENGTH_SHORT).show();
                }
                userName.setText("Name : " + name);
                userEmail.setText("Email : " + email);
                userDepartment.setText("Department : " + department);
                userLevel.setText("Level : " + level);
                userSemester.setText("Semester : " + semester);
                userSID.setText("Student ID : " + sID);
                userPhoneNumber.setText("Phone : " + phoneNumber);
                userSession.setText("Session : " + session);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
