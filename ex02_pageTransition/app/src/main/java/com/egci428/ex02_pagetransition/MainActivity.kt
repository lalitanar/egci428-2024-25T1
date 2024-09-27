package com.egci428.ex02_pagetransition

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mainText = findViewById<EditText>(R.id.mainEditText)
        val go2DetailBtn = findViewById<Button>(R.id.go2DetailBtn)

        go2DetailBtn.setOnClickListener {
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("input1",mainText.text.toString())
            startActivity(intent)
        }
        Log.d(TAG,"OnCreate")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"OnRestart")
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"OnStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"OnPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"OnDestroy")
    }
}