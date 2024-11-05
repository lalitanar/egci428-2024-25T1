package com.egci428.ex17_camera

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var photoBtn : Button
    lateinit var photoImageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        photoBtn = findViewById(R.id.photoButton)
        photoImageView = findViewById(R.id.photoImageView)
    }

    fun takePhoto(view: View){
        requestCameraPermission.launch(android.Manifest.permission.CAMERA)
    }
    private val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isSuccess : Boolean ->
            if(isSuccess){
                Log.d("Take Photo", "Permission Granted")
                takePicture.launch(null)
            } else {
                Toast.makeText(applicationContext, "Camera has no permission", Toast.LENGTH_SHORT).show()
            }
    }

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        bitmap : Bitmap? ->
        Log.d("Take Picture", "Show Bitmap picture")
        photoImageView.setImageBitmap(bitmap)
    }
}