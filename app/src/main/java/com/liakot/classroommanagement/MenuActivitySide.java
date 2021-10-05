package com.liakot.classroommanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivitySide extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String userEmail, userPass, userName;
    DatabaseReference databaseReference;
    TextView userNameTextView, userEmailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_side);

        databaseReference = FirebaseDatabase.getInstance().getReference("User Profile");

        //-----------for hide the app name from toolbar-----------------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        //-------------for menu Button in toolbar--------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);


        userEmail = getIntent().getStringExtra("Email");
        userPass = getIntent().getStringExtra("Password");
        userName = getIntent().getStringExtra("Name");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainPageActivity()).commit();
            navigationView.setCheckedItem(R.id.nav_all_buildings);
        }

        //-----------Access update profile Button from header layout-----------
        View header = navigationView.getHeaderView(0);

        Button updateProfileButton;

        ImageView userProfilePicture;

        updateProfileButton = header.findViewById(R.id.updateProfileButton);
        userProfilePicture = header.findViewById(R.id.userProfilePicture);
        userNameTextView = header.findViewById(R.id.userNameTextView);
        userEmailTextView = header.findViewById(R.id.userEmailTextView);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userId = userEmail.substring(0, userEmail.indexOf("@")) + userPass;

                AddUserProfile userProfile;
                userProfile = snapshot.child(userId).getValue(AddUserProfile.class);

                assert userProfile != null;
                userName = userProfile.getUserName();
                userEmail = userProfile.getUserEmail();

                userEmailTextView.setText(userEmail);
                userNameTextView.setText(userName);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivitySide.this, UpdateProfile.class);
                intent.putExtra("Email", userEmail);
                intent.putExtra("Password", userPass);

                startActivity(intent);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        TextView toolbarTextView;
        toolbarTextView = findViewById(R.id.toolbarMainTextView);
        switch (item.getItemId()) {
            case R.id.nav_profile:
                UserProfileFragment userProfileFragment = new UserProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putString("Email", userEmail);
                bundle.putString("Password", userPass);
                userProfileFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        userProfileFragment).commit();
                toolbarTextView.setText("User Profile");

                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;

            case R.id.nav_all_buildings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainPageActivity()).commit();
                toolbarTextView.setText("Academic Buildings");

                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;

            case R.id.cr_contact:
                Intent intent2 = new Intent(MenuActivitySide.this, CrContact.class);
                startActivity(intent2);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;

            case R.id.developer_name:
                Intent intent = new Intent(MenuActivitySide.this, ActivityDevelopers.class);
                startActivity(intent);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;

            case R.id.sign_out:
                finish();
                Intent intent1 = new Intent(MenuActivitySide.this, MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.emergency_contact:
                Intent intent4 = new Intent(MenuActivitySide.this, EmergencyContact.class);
                startActivity(intent4);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.contact_us:
                Intent intent5 = new Intent(MenuActivitySide.this, ContactWithUs.class);
                startActivity(intent5);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;
        }
        return true;
    }
}
