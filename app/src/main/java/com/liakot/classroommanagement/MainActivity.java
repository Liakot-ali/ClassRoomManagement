package com.liakot.classroommanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static final String LOGIN_PRE = "LogInInformation";
    Button loginUserRegisterButton;
    Button loginUserLoginButton;
    EditText logInUserEmail, logInUserPassword;
    ProgressBar progressBar;
    CheckBox rememberMe;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeAll();

    }

    private void InitializeAll() {

        mAuth = FirebaseAuth.getInstance();

        loginUserRegisterButton = findViewById(R.id.logInUserRegisterButton);
        loginUserLoginButton = findViewById(R.id.logInUserSignInButton);
        progressBar = findViewById(R.id.loginProgressbar);
        logInUserEmail = findViewById(R.id.logInUserEmail);
        logInUserPassword = findViewById(R.id.logInUserPassword);
        rememberMe = findViewById(R.id.logInRememberMe);

        loginUserRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginUserLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //------------- for hide the keyboard------
                InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                UserLogin();
            }
        });
    }

    private void UserLogin() {

        final String userEmail, userPass;
        userEmail = logInUserEmail.getText().toString().trim();
        userPass = logInUserPassword.getText().toString().trim();

        if(userEmail.isEmpty())
        {
            logInUserEmail.setError("Please enter your email address");
            logInUserEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
        {
            logInUserEmail.setError("Please enter a valid email");
            logInUserEmail.requestFocus();
            return;
        }
        if(userPass.isEmpty())
        {
            logInUserPassword.setError("Please enter your password");
            logInUserPassword.requestFocus();
            return;
        }
        if(userPass.length()<=6)
        {
            logInUserPassword.setError("Your password is > 6 digits");
            logInUserPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    if(rememberMe.isChecked())
                    {
                        // ----------- to skip login activity if user is logged in-----------
                        SharedPreferences preferences = getSharedPreferences(MainActivity.LOGIN_PRE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("hasLoggedIn", true);
                        editor.putString("userEmail", userEmail);
                        editor.putString("userPassword", userPass);
                        editor.apply();
                    }
                    Toast.makeText(getApplicationContext(), "Log In Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, MenuActivitySide.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    logInUserEmail.setText("");
                    logInUserPassword.setText("");
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
