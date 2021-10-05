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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileFragment extends Fragment {

    private ProgressBar progressBar;
    private DatabaseReference databaseReference;
    private String userEmailMain, userPassMain;
    private String name, email, department, level, semester, session, sID, phoneNumber;
    private TextView userName, userEmail, userDepartment, userLevel, userSemester, userSession, userPhoneNumber, userSID;
    private ImageView userProfilePicture;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_profile, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("User Profile");


        Bundle bundle = getArguments();
        if (bundle != null) {
            userEmailMain = bundle.getString("Email");
            userPassMain = bundle.getString("Password");
        }
        Button updateProfileButton = view.findViewById(R.id.updateProfileButton);
        progressBar=view.findViewById(R.id.progressBar2);
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

        name = "";
        email = "";
        department = "";
        semester = "";
        phoneNumber = "";

        level = "";
        sID = "";
        session = "";


        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);

                String userId = userEmailMain.substring(0, userEmailMain.indexOf("@")) + userPassMain;
                userName.setText(userId);

                AddUserProfile userProfile;
                userProfile = snapshot.child(userId).getValue(AddUserProfile.class);

                assert userProfile != null;
                name = userProfile.getUserName();
                email = userProfile.getUserEmail();
                department = userProfile.getUserDepartment();
                level = userProfile.getUserLevel();
                semester = userProfile.getUserSemester();
                sID = userProfile.getUserSID();
                phoneNumber = userProfile.getUserPhoneNumber();
                session = userProfile.getUserSession();

                userName.setText("Name : " + name);
                userEmail.setText("Email : " + email);
                userDepartment.setText("Department : " + department);
                userLevel.setText("Level : " + level);
                userSemester.setText("Semester : " + semester);
                userSID.setText("Student ID : " + sID);
                userPhoneNumber.setText("Phone Number : " + phoneNumber);
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
