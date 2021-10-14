package com.liakot.classroommanagement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivitySide extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //    private static final String LOGIN_PRE = "LogInInformation";
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    String userEmail, userName, profilePicture;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    TextView userNameTextView, userEmailTextView;
    Button updateProfileButton;
    CircleImageView userProfilePicture;
    TextView toolbarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_side);

        toolbarTextView = findViewById(R.id.toolbarMainTextView);

        //-----------for hide the app name from toolbar-----------------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String userUniqueId = mAuth.getUid();
        assert userUniqueId != null;
        DatabaseReference profileRef = database.getReference("Student").child("User").child(userUniqueId).child("Profile");

        //-------------for menu Button in toolbar--------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainPageActivity()).commit();
            navigationView.setCheckedItem(R.id.nav_all_buildings);
        }

        //-----------Access update profile Button from header layout-----------
        View header = navigationView.getHeaderView(0);

        updateProfileButton = header.findViewById(R.id.updateProfileButton);
        userProfilePicture = header.findViewById(R.id.userProfilePicture);
        userNameTextView = header.findViewById(R.id.userNameTextView);
        userEmailTextView = header.findViewById(R.id.userEmailTextView);

        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                AddUserInformation profile = snapshot.getValue(AddUserInformation.class);

                assert profile != null;
                userName = profile.getUserName();
                userEmail = profile.getUserEmail();
                profilePicture = profile.getProfilePicture();

                userEmailTextView.setText(userEmail);
                userNameTextView.setText(userName);
                if (!profilePicture.isEmpty()) {
                    Picasso.get().load(profile.getProfilePicture()).into(userProfilePicture);
                }

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

                startActivity(intent);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });

        userProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileFragment userProfileFragment = new UserProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        userProfileFragment).commit();
                toolbarTextView.setText("User Profile");
                navigationView.setCheckedItem(R.id.nav_profile);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    //----------Double press to exit---------
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //---------- double back press to exit
            if (onBackPressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            onBackPressedTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_profile:
                UserProfileFragment userProfileFragment = new UserProfileFragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        userProfileFragment).commit();
                toolbarTextView.setText("My Profile");

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

            case R.id.nav_my_room:
                Intent intent3 = new Intent(MenuActivitySide.this, MyRoomActivity.class);
                startActivity(intent3);
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Do you want to log out?");
                dialog.setIcon(R.drawable.ic_log_out);

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                    }
                });
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // ----------- set sharedPreference false-----------
                        SharedPreferences preferences = getSharedPreferences(MainActivity.LOGIN_PRE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("hasLoggedIn", false);
                        editor.apply();

                        mAuth.signOut();
                        Intent intent1 = new Intent(MenuActivitySide.this, MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                        finish();
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                    }
                });
                dialog.show();
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

    private long onBackPressedTime;

}
