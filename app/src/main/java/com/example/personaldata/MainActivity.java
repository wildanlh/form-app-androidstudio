package com.example.personaldata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView txtNIK, txtHi, txtName, txtPlaceOfBirth, txtDateOfBirth, txtAddress, txtGender, txtOccupation, txtMarital;
    private Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHi = findViewById(R.id.hi);
        txtNIK = findViewById(R.id.rslt_nik);
        txtName = findViewById(R.id.rslt_name);
        txtPlaceOfBirth = findViewById(R.id.rslt_placeofbirth);
        txtDateOfBirth = findViewById(R.id.rslt_dateofbirth);
        txtAddress = findViewById(R.id.rslt_address);
        txtGender = findViewById(R.id.rslt_gender);
        txtOccupation = findViewById(R.id.rslt_occupation);
        txtMarital = findViewById(R.id.rslt_maritalstatus);

        btnBack = findViewById(R.id.btn_back);

        txtHi.setText("Hi, " + getIntent().getStringExtra("name"));
        txtNIK.setText("" + getIntent().getStringExtra("nik"));
        txtName.setText("" + getIntent().getStringExtra("name"));
        txtPlaceOfBirth.setText("" + getIntent().getStringExtra("place_of_birth"));
        txtDateOfBirth.setText("" + getIntent().getStringExtra("date_of_birth"));
        txtAddress.setText("" + getIntent().getStringExtra("address"));
        txtGender.setText("" + getIntent().getStringExtra("gender"));
        txtOccupation.setText("" + getIntent().getStringExtra("occupation"));
        txtMarital.setText("" + getIntent().getStringExtra("marital"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}