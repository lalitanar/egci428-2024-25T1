package com.egci428.ex04_listactivity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    protected var data : ArrayList<Course>? = null

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

        data = DataProvider.getData()
        val courseArrayAdapter = CourseArrayAdapter(this,0, data!!)
        listView.adapter = courseArrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val aCourse = data!![position]
            sendDetail(aCourse, position)
        }


    }

    private fun sendDetail(course: Course, position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("courseTitle", course.title)
        intent.putExtra("courseDesc", course.description)
        intent.putExtra("courseNo", course.courseNumber)
        intent.putExtra("courseCredits", course.credits)
        intent.putExtra("imagepos", position)
        startActivity(intent)
    }

    class CourseArrayAdapter(var context: Context, resource: Int, var objects: ArrayList<Course>):
        BaseAdapter() {
        override fun getCount(): Int {
            return objects.size
        }

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val  aRow:View
            val whiteColor = Color.parseColor("#FFFFFF")
            val greyColor = Color.parseColor("#E5E5E5")

            val aCourse = objects[position]

            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(viewGroup!!.context)
                aRow = layoutInflater.inflate(R.layout.course_item, viewGroup,false)
                val titleTextView = aRow.findViewById<TextView>(R.id.titleTextItem)
                val imageCourse = aRow.findViewById<ImageView>(R.id.imageCourseItem)

                val viewHolder = ViewHolder(titleTextView, imageCourse)
                aRow.tag = viewHolder

            } else {
                aRow = convertView
            }

            val viewHolder = aRow.tag as ViewHolder
            viewHolder.titleTextView.text = aCourse.title

            val imgpos = position%3+1
            val res = context.resources.getIdentifier("image1010"+imgpos,"drawable", context.packageName)
            viewHolder.imageCourse.setImageResource(res)

            if(position%2==0) {
                aRow.setBackgroundColor(whiteColor)
            } else {
                aRow.setBackgroundColor(greyColor)
            }

            return aRow
        }

    }

    private class ViewHolder(val titleTextView: TextView, val imageCourse: ImageView)
}






