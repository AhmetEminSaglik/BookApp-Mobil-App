package com.ahmeteminsaglik.neo4jbookappandroid.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ahmeteminsaglik.neo4jbookappandroid.MyReadBookActivity;
import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.signup.SignUpActivity;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.StrictModePolicy;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictModePolicy.enable();
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        startLoginProcess();

        setAllBtnClickFunction();

    }

    private void setAllBtnClickFunction() {
        Button btnLogin = findViewById(R.id.loginPageBtnLogin);
        btnLogin.setOnClickListener(getLoginBtnFunction());

        Button btnSignUp = findViewById(R.id.loginPageBtnSignup);
        btnSignUp.setOnClickListener(getSignUpPageBtnFunction());

    }

    private View.OnClickListener getLoginBtnFunction() {
        Intent intent = new Intent(this, MyReadBookActivity.class);
        return view -> {
            User user = startLoginProcess();
            if (user != null) {
                setUserDataToIntent(intent, user);
                startActivity(intent);
            }
//                finish();
        };
    }

    private View.OnClickListener getSignUpPageBtnFunction() {
        Intent intent = new Intent(this, SignUpActivity.class);
        return view -> {
            startActivity(intent);
            finish();
        };
    }

    private User startLoginProcess() {
        LoginActivityProcess loginActivityProcess = new LoginActivityProcess(getApplicationContext());

        EditText eTxtUsername = findViewById(R.id.loginPageEditTxtUsername);
        EditText eTxtPassword = findViewById(R.id.loginPageEditTxtPassword);
        String username = getDataFromLoginForm(eTxtUsername);
        String password = getDataFromLoginForm(eTxtPassword);
        return loginActivityProcess.getLoginUser(username, password);

    }

    private String getDataFromLoginForm(EditText editText) {
        return editText.getText().toString();
    }

    private void setUserDataToIntent(Intent intent, User user) {

        intent.putExtra(EnumUser.ID.getName(), user.getId());
        intent.putExtra(EnumUser.NAME.getName(), user.getName());
        intent.putExtra(EnumUser.LASTNAME.getName(), user.getLastname());
        intent.putExtra(EnumUser.USERNAME.getName(), user.getUsername());
        intent.putExtra(EnumUser.LASTNAME.getName(), user.getPassword());
        intent.putExtra(EnumUser.TOTALFOLLOWERS.getName(), user.getTotalFollowers());
    }
}