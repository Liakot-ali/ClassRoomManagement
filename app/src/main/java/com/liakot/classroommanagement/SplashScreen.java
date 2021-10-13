package com.liakot.classroommanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    ProgressBar progressBar;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar6);
        progressBar.setVisibility(View.VISIBLE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences(MainActivity.LOGIN_PRE, Context.MODE_PRIVATE);
                boolean hasLoggedIn = preferences.getBoolean("hasLoggedIn", false);

                if(hasLoggedIn) {
                    //---------- get value from shared preferences-----------
                    String email = preferences.getString("userEmail", "example123@emmail.com");
                    String password = preferences.getString("userPassword", "ThisIsExample");

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(SplashScreen.this, MenuActivitySide.class);
//                                 intent.putExtra("Email",email);
//                                 intent.putExtra("Password",password);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }, 100);

    }

    //------- double back press to exit------
    private long onBackPressedTime;
    @Override
    public void onBackPressed() {
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
