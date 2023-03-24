package com.ahmeteminsaglik.neo4jbookappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = findViewById(R.id.loginPageBtnLogin);
        button.setOnClickListener(getBtnFunction());
    }

    View.OnClickListener getBtnFunction() {
        Intent intent = new Intent(this,MyReadBookActivity.class);
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
//                finish();
            }
        };
    }

}