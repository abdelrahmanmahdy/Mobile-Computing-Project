package com.fcis.abdelrahmantarek.mobilecomputing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email,pw;
    CheckBox remeber;
    Button login,register;
    TextView forgot;
    private boolean emailGood;
    private boolean pwGood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);
        //init UI
        email = findViewById(R.id.email_ET);
        pw = findViewById(R.id.pw_ET);
        remeber = findViewById(R.id.remember_CB);
        login = findViewById(R.id.login_BTN);
        register = findViewById(R.id.register_BTN);
        forgot = findViewById(R.id.fp_TV);

        forgot.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailIn = email.getText().toString();
                if(emailIn.isEmpty()){
                    emailGood=false;
                    email.setError(null);
                }else
                if (!Patterns.EMAIL_ADDRESS.matcher(emailIn).matches()) {
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
                if(password.isEmpty()){
                    pwGood=false;
                    pw.setError(null);
                }else
                if (password.length() < 8) {
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
        switch (v.getId()){
            case R.id.register_BTN:
                Toast.makeText(this,"Register",Toast.LENGTH_LONG).show();
                break;
            case R.id.login_BTN:
                Toast.makeText(this,"Login",Toast.LENGTH_LONG).show();
                break;
            case R.id.fp_TV:
                Toast.makeText(this,"Forgot password",Toast.LENGTH_LONG).show();
                break;

        }

    }
}
