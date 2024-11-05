package com.egci428.ex16_photo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
        val photoImageView = findViewById<ImageView>(R.id.photoImageView)
        val photoButton = findViewById<Button>(R.id.photoButton)

        var loadImage = registerForActivityResult(ActivityResultContracts.GetContent(), {
            photoImageView.setImageURI(it)
        })
        photoButton.setOnClickListener {

            loadImage.launch("image/*")
        }

    }
}