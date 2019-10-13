package com.example.modelpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    Button edit,delete,search;
    EditText username,dob,pw;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        username = (EditText) findViewById(R.id.us);
        dob = (EditText) findViewById(R.id.dob);
        pw = (EditText) findViewById(R.id.pw);

        edit = (Button)findViewById(R.id.edit);
        delete = (Button)findViewById(R.id.delete);
        search = (Button)findViewById(R.id.search);

        male = (RadioButton) findViewById(R.id.ma);
        female = (RadioButton) findViewById(R.id.fe);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());

               List user = dbHelper.readAllInfo(username.getText().toString());

               if (user.isEmpty())
               {

               }
               else
               {
                   username.setText(user.get(0).toString());
                   dob.setText(user.get(1).toString());
                   pw.setText(user.get(2).toString());

                   if (user.get(3).toString().equals("Male"))
                   {
                       male.setChecked(true);
                   }
                   else
                   {
                       female.setChecked(true);
                   }
               }


            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());

                if (male.isChecked())
                {
                    gender = "Male";
                }
                else
                {
                    gender = "Female";
                }

                Boolean x = dbHelper.updateInfo(username.getText().toString(),dob.getText().toString(),pw
                .getText().toString(),gender);

                if (x == true)
                {
                    Toast.makeText(EditProfileActivity.this, "UPDATED", Toast.LENGTH_SHORT).show();

                }



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());

                dbHelper.deleteInfo(username.getText().toString());

                username.setText(null);
                dob.setText(null);
                pw.setText(null);

                male.setChecked(false);
                female.setChecked(false);


            }
        });



    }
}
