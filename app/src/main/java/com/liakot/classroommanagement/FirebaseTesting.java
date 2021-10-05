package com.liakot.classroommanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseTesting extends AppCompatActivity {

    EditText userName;
    Button addMessage;
    DatabaseReference databaseReference;
    int userId = 1802035;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_testing);

        userName = findViewById(R.id.userNameForFire);
        addMessage = findViewById(R.id.addUserNameButton);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        addMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();

                databaseReference.child(String.valueOf(userId++)).setValue(name);

                Toast.makeText(getApplicationContext(), "Name Added", Toast.LENGTH_LONG).show();
                userName.setText("");
            }
        });


    }
}
