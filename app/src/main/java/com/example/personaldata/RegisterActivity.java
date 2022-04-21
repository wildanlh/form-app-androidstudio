package com.example.personaldata;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editName, editNIK, editPlaceOfBirth, editAddress;
    private Button btnCancel, btnNext, btnDate;
    private CheckBox cbTerm;
    private TextView txtTerm, txtDate, txtGender, txtSelectGender;
    private Spinner spinnerOccupation, spinnerMarital;
    private RadioGroup groupGender;
    private RadioButton btnMale, btnFemale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtTerm = findViewById(R.id.txt_term);
        txtDate = findViewById(R.id.date_txt);
        txtGender = findViewById(R.id.resultgender);
        txtSelectGender = findViewById(R.id.txt_gender);

        editName = findViewById(R.id.edit_name);
        editNIK= findViewById(R.id.edit_NIK);
        editPlaceOfBirth = findViewById(R.id.edit_placeofbirth);
        editAddress = findViewById(R.id.edit_address);

        groupGender = findViewById(R.id.radio_gender);

        btnCancel = findViewById(R.id.btn_cancel);
        btnNext = findViewById(R.id.btn_next);
        btnDate = findViewById(R.id.btn_dateofbirth);
        btnMale = findViewById(R.id.male);
        btnFemale = findViewById(R.id.female);

        spinnerOccupation = findViewById(R.id.spin_occupation);
        spinnerMarital = findViewById(R.id.spin_maritalstatus);

        cbTerm = findViewById(R.id.cb_term);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.occupation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOccupation.setAdapter(adapter);

        spinnerOccupation.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.marital, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarital.setAdapter(adapter2);

        spinnerMarital.setOnItemSelectedListener(this);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; ++i)
                {
                    if (!Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ]*").matcher(String.valueOf(source.charAt(i))).matches())
                    {
                        return "";
                    }
                }

                return null;
            }
        };

        editName.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(100)});
        editPlaceOfBirth.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(15)});

        groupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.male:
                        txtGender.setText("Male");
                        break;
                    case R.id.female:
                        txtGender.setText("Female");
                        break;
                }
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        txtDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        txtTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://instagram.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editNIK.getText().toString().equals("")) {
                    editNIK.setError("Empty NIK");
                    Toast.makeText(RegisterActivity.this, "Input your NIK", Toast.LENGTH_SHORT).show();
                } else if (editName.getText().toString().equals("")) {
                    editName.setError("Empty Name");
                    Toast.makeText(RegisterActivity.this, "Input your Name", Toast.LENGTH_SHORT).show();
                } else if (editPlaceOfBirth.getText().toString().equals("")) {
                    editPlaceOfBirth.setError("Empty Place of Birth");
                    Toast.makeText(RegisterActivity.this, "Input your Place of Birth", Toast.LENGTH_SHORT).show();
                } else if (txtDate.getText().toString().equals("")) {
                    txtDate.setError("Select your Date of Birth");
                    Toast.makeText(RegisterActivity.this, "Select your Date of Birth", Toast.LENGTH_SHORT).show();
                } else if (editAddress.getText().toString().equals("")) {
                    editAddress.setError("Empty Address");
                    Toast.makeText(RegisterActivity.this, "Select your Address", Toast.LENGTH_SHORT).show();
                } else if (txtGender.getText().toString().equals("")) {
                    txtSelectGender.setError("Select Your Gender");
                    Toast.makeText(RegisterActivity.this, "Select your Gender", Toast.LENGTH_SHORT).show();
                } else if (spinnerOccupation.getSelectedItemPosition() == 0) {
                    ((TextView)spinnerOccupation.getSelectedView()).setError("Error message");
                    Toast.makeText(RegisterActivity.this, "Select your Occupation", Toast.LENGTH_SHORT).show();
                } else if (spinnerMarital.getSelectedItemPosition() == 0) {
                    ((TextView)spinnerMarital.getSelectedView()).setError("Error message");
                    Toast.makeText(RegisterActivity.this, "Select your Marital Status", Toast.LENGTH_SHORT).show();
                } else if (!cbTerm.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "Terms and Conditions must be accepted", Toast.LENGTH_SHORT).show();
                } else {
                    if (editNIK.getError() == null && editName.getError() == null && editPlaceOfBirth.getError() == null && txtDate.getError() == null &&
                           editAddress.getError() == null && spinnerOccupation.getSelectedItemPosition() > 0 && spinnerMarital.getSelectedItemPosition() > 0 && cbTerm.isChecked()) {

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                        intent.putExtra("nik", editNIK.getText().toString());
                        intent.putExtra("name", editName.getText().toString());
                        intent.putExtra("place_of_birth", editPlaceOfBirth.getText().toString());
                        intent.putExtra("date_of_birth", txtDate.getText().toString());
                        intent.putExtra("address", editAddress.getText().toString());
                        intent.putExtra("gender", txtGender.getText().toString());
                        intent.putExtra("occupation", spinnerOccupation.getSelectedItem().toString());
                        intent.putExtra("marital", spinnerMarital.getSelectedItem().toString());

                        startActivity(intent);
                    }
                editNIK.setText("");
                editName.setText("");
                editPlaceOfBirth.setText("");
                txtDate.setText("");
                editAddress.setText("");
                txtGender.setText("");
                btnMale.setChecked(false);
                btnFemale.setChecked(false);
                spinnerOccupation.setSelection(0);
                spinnerMarital.setSelection(0);
                cbTerm.setChecked(false);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
