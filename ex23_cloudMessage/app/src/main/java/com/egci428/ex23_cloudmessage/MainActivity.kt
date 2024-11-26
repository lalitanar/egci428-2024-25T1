package com.egci428.ex23_cloudmessage

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var titleText = findViewById<TextView>(R.id.titleText)
        var bodyText = findViewById<TextView>(R.id.bodyText)

        val bundle = intent.extras
        val mTitle = bundle?.getString("mTitle")
        val mBody = bundle?.getString("mBody")

        titleText.text = mTitle
        bodyText.text = mBody

        //Toast.makeText(this, mBody,Toast.LENGTH_LONG).show()

    }
}