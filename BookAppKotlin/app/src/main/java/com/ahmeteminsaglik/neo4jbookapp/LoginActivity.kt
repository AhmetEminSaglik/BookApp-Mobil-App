package com.ahmeteminsaglik.neo4jbookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var text:String="Ahmet Emin"
        println("text :$text ")
        var btnExplore = findViewById<Button>(R.id.loginPageBtnLogin)
        btnExplore.setOnClickListener {
            val nextPage = Intent(this, MyReadBookActivity::class.java)
            startActivity(nextPage)
            finish();
        }
    }
}