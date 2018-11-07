package com.example.theba.java_naja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference current_user_db, current_user_db_fName, current_user_db_lName;
    DatabaseReference myRef;

    private EditText Email, FName, LName, Password, ConfirmPassword;
    private String userEmail, userFname, userLname, userPwd, userConPwd, user_id;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setTitle("แก้ไขข้อมูลส่วนตัว");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();

        user_id = user.getUid();
        current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


        Email = (EditText) findViewById(R.id.emailuser);
        FName = (EditText) findViewById(R.id.fName);
        LName = (EditText) findViewById(R.id.lName);
        //Password = (EditText) findViewById(R.id.pwduser);
        //ConfirmPassword = (EditText) findViewById(R.id.pwduser2);
        Submit = (Button) findViewById(R.id.accept);

        Email.setText(user.getEmail());
        Email.setEnabled(false);

        current_user_db_fName = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("FName");
        current_user_db_fName.addValueEventListener(new ValueEventListener() {
            final ProgressDialog progressDialog = ProgressDialog.show(EditProfileActivity .this, "Please wait...", "Loading...", true, true);
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Fname = dataSnapshot.getValue(String.class);
                FName.setText(Fname);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        current_user_db_lName = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("LName");
        current_user_db_lName.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //final ProgressDialog progressDialog = ProgressDialog.show(EditProfileActivity .this, "Please wait...", "Loading...", true, true);
                String Lname = dataSnapshot.getValue(String.class);
                LName.setText(Lname);
                //progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userEmail = Email.getText().toString();
                userFname = FName.getText().toString();
                userLname = LName.getText().toString();
                //userPwd = Password.getText().toString();
                //userConPwd = ConfirmPassword.getText().toString();

                //if(userPwd.equals(userConPwd)) {
                    //setting rule can update data
                    //***adding info user***

                    Map newPost = new HashMap();
                    newPost.put("Email", userEmail);
                    newPost.put("FName", userFname);
                    newPost.put("LName", userLname);
                    //newPost.put("Password", userPwd);
                    current_user_db.updateChildren(newPost);

                    Intent intent = new Intent(EditProfileActivity.this,MainActivity.class);
                    Toast.makeText(EditProfileActivity.this,"ทำการบันทึกประวัติของคุณแล้ว",Toast.LENGTH_LONG).show();
                    finish();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                /*} else {
                    Toast.makeText(EditProfileActivity.this,"รหัสผ่านไม่ตรงกัน กรุณาตรวจสอบ",Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //do whatever
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
