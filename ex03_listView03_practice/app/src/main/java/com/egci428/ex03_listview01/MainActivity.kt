package com.egci428.ex03_listview01

import android.content.Context
import android.graphics.Color
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
        private val dayList = arrayListOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        private val colorDays = arrayListOf<String>("#FCF3CF", "#F5EEF8", "#D4EFDF", "#FDEBD0", "#D6EAF8", "#E8DAEF", "#F2D7D5")
        private val noWeeks: Int = 7


        private val mContext:Context
        init {
            mContext = context
        }

       fun insert(string) {
            wordList.add("")
           notifyDataSetChanged()
        }
        override fun getCount(): Int {
            return dayList.size*7
        }

        override fun getItem(position: Int): Any {
            return dayList[position%7]
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
            viewHolder.nameTextView.text = dayList[position%7]
            viewHolder.positionTextView.text = "position: "+position.toString()

            //if(position%7==0) {
                aRow.setBackgroundColor(Color.parseColor(colorDays[position%7]))
            //}
//            } else {
//                aRow.setBackgroundColor(Color.parseColor("#E0E0E0"))
//            }


            aRow.setOnClickListener {

                aRow.animate().setDuration(1500).alpha(0F).withEndAction(
                    Runnable {
                        dayList.removeAt(position)
                        notifyDataSetChanged()
                        aRow.alpha = 1.0F
                    }
                )

                //Toast.makeText(mContext ,wordList[position], Toast.LENGTH_SHORT).show()
            }


            return aRow
        }

    }
    private class ViewHolder(val nameTextView:TextView, val positionTextView:TextView)
}




