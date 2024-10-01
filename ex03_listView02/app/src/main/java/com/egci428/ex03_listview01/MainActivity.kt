package com.egci428.ex03_listview01

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
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
        val listView = findViewById<ListView>(R.id.listView)

        /*val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,wordList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,wordList[position], Toast.LENGTH_SHORT).show()
        }*/

        val adapter = MyCustomAdapter(this)
        listView.adapter = adapter


    }

    class MyCustomAdapter(context:Context): BaseAdapter() {
        private val wordList = arrayOf("ant", "bat", "cat", "dog",
            "elephant", "fox", "ant", "bat", "cat", "dog",
            "elephant", "fox", "ant", "bat", "cat", "dog",
            "elephant", "fox")
        private val mContext:Context
        init {
            mContext = context
        }


        override fun getCount(): Int {
            return wordList.size
        }

        override fun getItem(position: Int): Any {
            return wordList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val  aRow:View

            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(viewGroup!!.context)
                aRow = layoutInflater.inflate(R.layout.row, viewGroup,false)
                val nameTextView = aRow.findViewById<TextView>(R.id.nameTextView)
                val positionTextView = aRow.findViewById<TextView>(R.id.positionTextView)

                val viewHolder = ViewHolder(nameTextView, positionTextView)
                aRow.tag = viewHolder

            } else {
                aRow = convertView
            }

            val viewHolder = aRow.tag as ViewHolder
            viewHolder.nameTextView.text = wordList[position]
            viewHolder.positionTextView.text = "position: "+position.toString()

            aRow.setOnClickListener {
                Toast.makeText(mContext ,wordList[position], Toast.LENGTH_SHORT).show()
            }


            return aRow
        }

    }
    private class ViewHolder(val nameTextView:TextView, val positionTextView:TextView)
}




