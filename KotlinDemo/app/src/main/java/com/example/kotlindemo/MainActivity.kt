package com.example.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    var authorListURL = "http://10.0.2.2:8080/author/";
    var bookListURL = "http://10.0.2.2:8080/book/";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Volley.newRequestQueue(applicationContext);

        var btnAuthor = findViewById(R.id.btnAuthor) as Button
        var btnAuthorList = findViewById(R.id.btnAuthorList) as Button
        var btnBook = findViewById(R.id.btnBook) as Button
        var btnBookList = findViewById(R.id.btnBookList) as Button

        btnAuthor.setOnClickListener { authorBtnEvent() }
        btnAuthorList.setOnClickListener { authorListBtnEvent() }
        btnBook.setOnClickListener { bookBtnEvent() }
        btnBookList.setOnClickListener { bookListBtnEvent() }
    }

    fun authorBtnEvent() {
        try {
            var editTextNumber = findViewById(R.id.editTextNumber) as EditText
            var text = editTextNumber.text.toString()
            var id = text.toInt()
            var newURL = authorListURL + id;
            getDataJsonObject(newURL)
        } catch (e: java.lang.Exception) {
            Toast.makeText(this@MainActivity, "Fail to get response : $e", Toast.LENGTH_LONG).show()
            print("ERROR OCCURED : $e")
        }
    }

    fun authorListBtnEvent() {
        try {
            getDataJsonArray(authorListURL)
        } catch (e: java.lang.Exception) {
            Toast.makeText(this@MainActivity, "Fail to get response : $e", Toast.LENGTH_LONG).show()
            print("ERROR OCCURED : $e")
        }
    }

    fun bookBtnEvent() {
        try {
            var editTextNumber = findViewById(R.id.editTextNumber) as EditText
            var text = editTextNumber.text.toString() as String
            var id = text.toInt()
            var newURL = bookListURL + id;
            getDataJsonObject(newURL)
        } catch (e: java.lang.Exception) {
            Toast.makeText(this@MainActivity, "Fail to get response : $e", Toast.LENGTH_LONG).show()
            println("ERROR OCCURED : $e")
        }
    }

    fun bookListBtnEvent() {
        try {
            getDataJsonArray(bookListURL)
        } catch (e: java.lang.Exception) {
            Toast.makeText(this@MainActivity, "Fail to get response : $e", Toast.LENGTH_LONG).show()
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

    fun handleAPIResponse(responseString: String) {
        try {
            val text: String = responseString;
            Toast.makeText(applicationContext, "Data is retrieved", Toast.LENGTH_SHORT).show()
            var textId: TextView = findViewById(R.id.textId)
            textId.setText(text)
        } catch (e: Exception) {
            e.printStackTrace()
            print("ERROR OCCURED : $e")
        }
    }

    fun handleAPIError(error: String) {
        println("--> OCCURED ERROR IN RESPONSE :  $error")
        Toast.makeText(
            this@MainActivity,
            "Fail to get response : $error",
            Toast.LENGTH_LONG
        ).show()
    }
}
