package com.ahmeteminsaglik.neo4jbookappandroid.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.MyReadBookActivity;
import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        startLoginProcess();
        Button button = findViewById(R.id.loginPageBtnLogin);
        button.setOnClickListener(getBtnFunction());
    }

    View.OnClickListener getBtnFunction() {
        Intent intent = new Intent(this, MyReadBookActivity.class);
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = startLoginProcess();
                if(user!=null){
//                startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Username or pasword is wrong", Toast.LENGTH_LONG).show();
                }

//                finish();
            }
        };
    }

    private User startLoginProcess() {
        LoginActivityProcess loginActivityProcess = new LoginActivityProcess(getApplicationContext());

        EditText eTxtUsername = findViewById(R.id.loginPageEditTxtUsername);
        EditText eTxtPassword = findViewById(R.id.loginPageEditTxtPassword);
        String username = getDataFromLoginForm(eTxtUsername);
        String password = getDataFromLoginForm(eTxtPassword);
        User user = loginActivityProcess.login(username, password);
        return user;
    }

    private String getDataFromLoginForm(EditText editText) {
        return editText.getText().toString();
    }
}