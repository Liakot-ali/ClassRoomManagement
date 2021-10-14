package com.liakot.classroommanagement;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactWithUs extends AppCompatActivity {

    Toolbar toolbar;
    EditText commentText;
    Button commentBtn;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_with_us);

        //------------Toolbar Back Button----------
        toolbar = findViewById(R.id.toolbarDemo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        String userId = mAuth.getUid();
        assert userId != null;
        DatabaseReference commentRef = database.getReference("Student").child("Comment").child(userId).child("Text");

        commentText = findViewById(R.id.commentText);
        commentBtn = findViewById(R.id.commentButton);
        progressBar = findViewById(R.id.progressBar8);

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                methodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                progressBar.setVisibility(View.VISIBLE);
                String  comment = commentText.getText().toString();
                commentRef.setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ContactWithUs.this, "Thanks for your opinion", Toast.LENGTH_SHORT).show();
                            commentText.setText("");
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ContactWithUs.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    //---------------------For Toolbar Back Button------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
