package com.ahmeteminsaglik.neo4jbookappandroid.activities.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.login.LoginActivity;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.StrictModePolicy;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        StrictModePolicy.enable();
        setAllBtnClickFunction();
    }

    private void setAllBtnClickFunction() {
        Button btnBackToLogin = findViewById(R.id.signUpPageBtnLogin);
        btnBackToLogin.setOnClickListener(getBackToLoginPageBtnFunction());

        Button btnSignUp = findViewById(R.id.signUpPageBtnSignUp);
        btnSignUp.setOnClickListener(getSignUpBtnFunction());
    }


    private View.OnClickListener getBackToLoginPageBtnFunction() {
//        Intent intent = new Intent(this, LoginActivity.class);
        return view -> {
//            startActivity(intent);
            changeToLoginActivity();
            finish();
        };
    }

    private View.OnClickListener getSignUpBtnFunction() {
        return view -> {
            startSignUpProcess();
//            finish();
        };
    }

    private void startSignUpProcess() {
        SignUpActivityProcess signUpActivityProcess = new SignUpActivityProcess(getApplicationContext());

        EditText eTxtName = findViewById(R.id.signUpPageEditTxtName);
        EditText eTxtLastname = findViewById(R.id.signUpPageEditTxtLastname);
        EditText eTxtUsername = findViewById(R.id.signUpPageEditTxtUsername);
        EditText eTxtPassword = findViewById(R.id.signUpPageEditTxtPassword);

        String name = getDataFromEditTxt(eTxtName);
        String lastname = getDataFromEditTxt(eTxtLastname);
        String username = getDataFromEditTxt(eTxtUsername);
        String password = getDataFromEditTxt(eTxtPassword);

        User user = new User();
        user.setName(name);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(password);

        boolean result = signUpActivityProcess.signUp(user);
        if (result) {
            changeToLoginActivity();
        }

    }

    private String getDataFromEditTxt(EditText editText) {
        return editText.getText().toString();
    }

    private void changeToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}