package com.example.formforsubmit1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    CheckBox terms;
    RadioGroup radioBtnGroup;
    RadioButton btnMale;
    RadioButton btnFemale;
    Button registerBtn;
    EditText firstName;
    EditText lastName;
    EditText address;
    EditText email;
    EditText dob;
    TextView respond;
    String respondStr;
    DatePicker datePicker;
    Calendar calendar;
    TextView dateView;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        terms = (CheckBox) findViewById(R.id.terms);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        radioBtnGroup = (RadioGroup) findViewById(R.id.radioBtnGroup);
        btnMale = (RadioButton) findViewById(R.id.btnMale);
        btnFemale = (RadioButton) findViewById(R.id.btnFemale);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        address = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.email);

        respond = findViewById(R.id.respond);

        dateView = (TextView) findViewById(R.id.dob);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!terms.isChecked())
                {
                    respondStr = "";
                    respondStr = "Please agree with the term to continue! ";
                    respond.setText(respondStr);
                }
                else {
                    respondStr = "";
                    if (firstName.getText().length() == 0) {
                        respondStr += "First name, ";
                    }
                    if (lastName.getText().length() == 0) {
                        respondStr += "Last name, ";
                    }
                    if (address.getText().length() == 0) {
                        respondStr += "Address, ";
                    }
                    if (lastName.getText().length() == 0) {
                        respondStr += "Email, ";
                    }
                    if (dateView.getText().length() == 0) {
                        respondStr += "DOB, ";
                    }
                    int radioId = radioBtnGroup.getCheckedRadioButtonId();
                    if (btnMale.getId() != radioId && btnFemale.getId() != radioId){
                        respondStr += "Gender, ";
                    }
                    respond.setText(removeLastCharacter(respondStr) + " is missing, please fill all the form!");
                }

            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected DatePickerDialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public static String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 2);
        }
        return result;
    }
}