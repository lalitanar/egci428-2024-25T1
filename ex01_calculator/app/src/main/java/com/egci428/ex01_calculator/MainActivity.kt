package com.egci428.ex01_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        val resultText = findViewById<TextView>(R.id.resultTextView)
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        val minusBtn = findViewById<Button>(R.id.minusBtn)
        val editText = findViewById<EditText>(R.id.editText)
        val editText2 = findViewById<EditText>(R.id.editText2)

        submitBtn.setOnClickListener {
            var input1 = editText.text.toString().toInt()
            var input2 = editText2.text.toString().toInt()

            resultText.text = (input1 + input2).toString()
        }
        minusBtn.setOnClickListener {

        }




    }
}