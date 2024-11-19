package com.egci428.ex22_simplenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val Channel_ID = "100"
    private val RequestCode = 1001

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private val notificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val notificationBtn = findViewById<Button>(R.id.notificationBtn)

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            refreshPermissionStatus()
            if (it) {
                notifyMessage("EGCI428", "Hello World!!")
            } else {
                Snackbar.make(findViewById<View>(android.R.id.content).rootView, "Please grant Notification permission from Setting", Snackbar.LENGTH_LONG).show()
            }
        }

        createNotificationChannel()

        notificationBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notifyMessage("EGCI428", "Hello World!!")
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        refreshPermissionStatus()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "EGCI428"
            var descript = "EGCI4128 Notification Channel"
            var importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Channel_ID, name, importance).apply {
                description = descript
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun notifyMessage(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)

        val intent2 = Intent(this, SecondActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this,RequestCode, intent,PendingIntent.FLAG_IMMUTABLE)

        val pendingIntent2 = PendingIntent.getActivity(this,RequestCode, intent2,PendingIntent.FLAG_IMMUTABLE)

        val optionAction = NotificationCompat.Action.Builder(R.drawable.image10103, "Main Page",pendingIntent).build()

        val optionAction2 = NotificationCompat.Action.Builder(R.drawable.image10103, "Second Page",pendingIntent2).build()

        val icon = BitmapFactory.decodeResource(resources, R.drawable.user)
        val mBuilder = NotificationCompat.Builder(this, Channel_ID)
            .setLargeIcon(icon)
            .setSmallIcon(R.drawable.image10103)
            .setContentTitle(title)
            .setContentText(message)
            .addAction(optionAction)
            .addAction(optionAction2)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //.setWhen(System.currentTimeMillis()+10000)

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        notificationManager.notify(0,mBuilder.build())

    }

    private fun refreshPermissionStatus() {
        findViewById<TextView>(R.id.textView).text =
            if (notificationManager.areNotificationsEnabled())
                "Permission: True" else "Permission: False"
    }


}