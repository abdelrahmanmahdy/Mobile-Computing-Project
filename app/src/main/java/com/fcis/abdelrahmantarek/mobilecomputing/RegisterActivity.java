package com.fcis.abdelrahmantarek.mobilecomputing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    DatePicker calendarView;
    Button register, bd;
    EditText email, pw, confirmedPW;
    boolean emailGood,pwGood,bdGood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //init UI
        calendarView = findViewById(R.id.calender);
        register = findViewById(R.id.register_BTN);
        bd = findViewById(R.id.bd_BTN);
        email = findViewById(R.id.email_ET);
        pw = findViewById(R.id.pw_ET);
        confirmedPW = findViewById(R.id.confirmedPW_ET);
        //buttons on click listener
        register.setOnClickListener(this);
        bd.setOnClickListener(this);

        //DatePicker stuff
        Calendar c = Calendar.getInstance();
        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int month = c.get(Calendar.MONTH);
        final int mYear = c.get(Calendar.YEAR);

        final int minYear = 1960;
        final int minMonth = 0; // january
        final int minDay = 25;

        calendarView.init(mYear - 18, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (year < 1960)
                    view.updateDate(minYear, minMonth, minDay);

                if (monthOfYear < minMonth && year == minYear)
                    view.updateDate(minYear, minMonth, minDay);

                if (dayOfMonth < minDay && year == minYear && monthOfYear == minMonth)
                    view.updateDate(minYear, minMonth, minDay);


                if (year > mYear)
                    view.updateDate(mYear, month, day);

                if (monthOfYear > month && year == mYear)
                    view.updateDate(mYear, month, day);

                if (dayOfMonth > day && year == mYear && monthOfYear == month)
                    view.updateDate(mYear, month, day);


                calendarView.setVisibility(View.GONE);
                bd.setVisibility(View.VISIBLE);
                bd.setText("birthday: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                bdGood=true;
            }
        });

        //information checkers
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailIn = email.getText().toString();
                if (emailIn.isEmpty()) {
                    emailGood = false;
                    email.setError(null);
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailIn).matches()) {
                    email.setError("Wrong email format!");
                    emailGood = false;
                } else {
                    email.setError(null);
                    emailGood = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = pw.getText().toString();
                if (password.isEmpty()) {
                    pwGood = false;
                    pw.setError(null);
                } else if (password.length() < 8) {
                    pw.setError("Password too short!");
                    pwGood = false;
                } else {
                    pw.setError(null);
                    pwGood = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bd_BTN:
                calendarView.setVisibility(View.VISIBLE);
                bd.setVisibility(View.GONE);
                break;
            case R.id.register_BTN:
                if(!confirmedPW.getText().toString().equals(pw.getText().toString())){
                    Toast.makeText(this,"passwords do not match",Toast.LENGTH_LONG).show();
                    break;
                }else if(emailGood && pwGood && bdGood){
                    Intent intent = new Intent(this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
