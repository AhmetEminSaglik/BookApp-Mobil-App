package com.ahmeteminsaglik.neo4jbookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MyReadBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_read_book)
        Volley.newRequestQueue(applicationContext);
        var btnUserList = findViewById(R.id.myBookPagebtnGetAllPersonData) as Button
        btnUserList.setOnClickListener { getAllUserListBtnEvent() }
    }

    var userListURL = "http://10.0.2.2:8080/users/";

    fun handleAPIResponse(responseString: String) {
        try {
            val text: String = responseString;
            Toast.makeText(applicationContext, "Data is retrieved", Toast.LENGTH_SHORT).show()
            var textId: TextView = findViewById(R.id.myBookPageUserData)
            textId.setText(text)
        } catch (e: Exception) {
            e.printStackTrace()
            print("ERROR OCCURED : $e")
        }
    }

    fun handleAPIError(error: String) {
        println("--> OCCURED ERROR IN RESPONSE :  $error")
        Toast.makeText(
            this@MyReadBookActivity,
            "Fail to get response : $error",
            Toast.LENGTH_LONG
        ).show()
    }

    fun getAllUserListBtnEvent() {
        try {
            getDataJsonArray(userListURL)
        } catch (e: java.lang.Exception) {
            Toast.makeText(this@MyReadBookActivity, "Fail to get response : $e", Toast.LENGTH_LONG)
                .show()
            print("ERROR OCCURED : $e")
        }
    }


    fun getDataJsonObject(url: String) {
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response -> handleAPIResponse(response.toString()) },
            { error -> handleAPIError(error.toString()) })
        queue.add(request)

    }

    fun getDataJsonArray(url: String) {


        val queue: RequestQueue = Volley.newRequestQueue(applicationContext);
        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response -> handleAPIResponse(response.toString()) },
            { error -> handleAPIError(error.toString()) })
        queue.add(request)

    }
}