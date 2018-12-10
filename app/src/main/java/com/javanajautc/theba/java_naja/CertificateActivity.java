package com.javanajautc.theba.java_naja;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class CertificateActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user;
    TextView username;
    DatabaseReference current_user_db, current_user_db_fName, current_user_db_lName;
    private String Fname, Lname, user_id, imgLocation;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_certificate);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();

        user_id = user.getUid();
        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        username = findViewById(R.id.Cert_username);

        current_user_db_fName = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("FName");
        current_user_db_fName.addValueEventListener(new ValueEventListener() {
            //final ProgressDialog progressDialog = ProgressDialog.show(CertificateActivity .this, "Please wait...", "Loading...", true, true);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Fname = dataSnapshot.getValue(String.class);
                //progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        current_user_db_lName = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("LName");
        current_user_db_lName.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ProgressDialog progressDialog = ProgressDialog.show(CertificateActivity .this, "Please wait...", "Loading...", true, true);
                Lname = dataSnapshot.getValue(String.class);
                username.setText(Fname + " " + Lname);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
