package com.egci428.ex24_emailauth

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signInBtn = findViewById<Button>(R.id.signInBtn)
        val signOutBtn = findViewById<Button>(R.id.signOutBtn)
        val createUserBtn = findViewById<Button>(R.id.createUserBtn)

        auth = Firebase.auth

        signInBtn.setOnClickListener {
            signIn("egci428@example.com","12345678")
        }

        signOutBtn.setOnClickListener {
            signOut()
        }
        createUserBtn.setOnClickListener {
            createAccount("hello@example.com","12345678")
        }


    }


    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun signOut(){
        Firebase.auth.signOut()
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                task ->
                if(task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail: success")
                } else {
                    Log.d(TAG, "signInWithEmail: failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail: success")
                    val user = auth.currentUser
                } else {
                    Log.d(TAG, "createUserWithEmail: failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload() {
        TODO("Go to home page")
    }

    companion object {
        private const val TAG = "EmailPassword"
    }


}