package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPageActivity extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main_page, container, false);

        Button wajedBuildingButton, academicBuilding1, academicBuilding2, academicBuilding3, academicBuilding4;

        wajedBuildingButton = view.findViewById(R.id.wajedBuildingButton);
        academicBuilding1 = view.findViewById(R.id.academicBuilding1);
        academicBuilding2 = view.findViewById(R.id.academicBuilding2);
        academicBuilding3 = view.findViewById(R.id.academicBuilding3);
        academicBuilding4 = view.findViewById(R.id.academicBuilding4);

        wajedBuildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), WajedBuilding.class);
                startActivity(intent);
            }
        });
        academicBuilding1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AcademicBuilding1.class);
                startActivity(intent);
            }
        });
        academicBuilding2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AcademicBuilding2.class);
                startActivity(intent);
            }
        });
        academicBuilding3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AcademicBuilding3.class);
                startActivity(intent);
            }
        });
        academicBuilding4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AcademicBuilding4.class);
                startActivity(intent);
            }
        });

        return view;
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_page);
//        Toolbar toolbar = findViewById(R.id.toolbarDemo);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setTitle(null);
//        InitializeAll();
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return true;
//    }


}
