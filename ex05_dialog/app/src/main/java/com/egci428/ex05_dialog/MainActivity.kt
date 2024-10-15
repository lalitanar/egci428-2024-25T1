package com.egci428.ex05_dialog

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Arrays

class MainActivity : AppCompatActivity() {
    lateinit var alertButton: Button
    lateinit var alertButton2: Button
    lateinit var alertButton3: Button

    lateinit var selectedView: TextView
    lateinit var selectedView2: TextView
    lateinit var selectedView3: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        alertButton = findViewById(R.id.alertButton)
        alertButton2 = findViewById(R.id.alertButton2)
        alertButton3 = findViewById(R.id.alertButton3)

        selectedView = findViewById(R.id.selectedView)
        selectedView2 = findViewById(R.id.selectedView2)
        selectedView3 = findViewById(R.id.selectedView3)

        //Alert dialog: yes/no
        alertButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do you want to delete this item?")
            builder.setTitle("Alert!")
            builder.setCancelable(false)

            builder.setPositiveButton("Yes") { dialog, which ->
                selectedView.text = "Selected Item id: Yes"
            }
            builder.setNegativeButton("No") {dialog, which ->
                dialog.cancel()
                selectedView.text = "Selected Item id: No"
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        //Alert dialog: option
        val checkedItem = intArrayOf(-1)

        alertButton2.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setIcon(R.drawable.ic_launcher_background)
            alertDialog.setTitle("Choose one item")
            val listItems = arrayOf("Ant", "Bat", "Cat", "Dog")
            alertDialog.setSingleChoiceItems(listItems,checkedItem[0]){ dialog, which ->
                checkedItem[0] = which
                selectedView2.text = "Selected item is : " + listItems[which]
                dialog.dismiss()
            }
            alertDialog.setNegativeButton("Cancel") { dialog, which -> }
            val customAlertDialog = alertDialog.create()
            customAlertDialog.show()
        }

        val listItem3 = arrayOf("C", "C++", "Python", "Java")
        val checkedItem3 = BooleanArray(listItem3.size)
        val selectedItem3 = mutableListOf(*listItem3)

        alertButton3.setOnClickListener {
            selectedView3.text = null
            val builder3 = AlertDialog.Builder(this)
            builder3.setTitle("Choose the programming languages")
            builder3.setIcon(R.drawable.ic_launcher_foreground)

            builder3.setMultiChoiceItems(listItem3, checkedItem3){ dialog, which, isChecked ->
                checkedItem3[which] = isChecked
                val currentItem = selectedItem3[which]
            }
            builder3.setCancelable(false)

            builder3.setPositiveButton("Done") { dialog, which ->
                for (i in checkedItem3.indices) {
                    if(checkedItem3[i]){
                        selectedView3.text = String.format("%s%s, ", selectedView3.text, selectedItem3[i])
                    }
                }
            }
            builder3.setNegativeButton("Clear All") { dialog, which ->
                Arrays.fill(checkedItem3, false)
            }

            val alertDialog3 = builder3.create()
            alertDialog3.show()
        }


    }
}