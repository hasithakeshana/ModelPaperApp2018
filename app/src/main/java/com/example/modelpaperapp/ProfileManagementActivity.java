package com.example.modelpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ProfileManagementActivity extends AppCompatActivity {

    Button update,add;
    EditText username,dob,pw;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);


        username = (EditText) findViewById(R.id.uname);
        dob = (EditText) findViewById(R.id.dob);
        pw = (EditText) findViewById(R.id.pw);

        update = (Button)findViewById(R.id.update);
        add = (Button)findViewById(R.id.add);

        male = (RadioButton) findViewById(R.id.Male);
        female = (RadioButton) findViewById(R.id.Female);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addInformation();
            }
        });




    }

    private void addInformation() {

        if (male.isChecked())
        {
            gender = "Male";
        }
        else
        {
            gender = "Female";
        }

        DBHelper db = new DBHelper(getApplicationContext());

       long newId = db.addInfo(username.getText().toString(),dob.getText().toString(),pw.getText().toString(),gender);


        Toast.makeText(this, "added"+newId, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);
        startActivity(intent);


    }
}
