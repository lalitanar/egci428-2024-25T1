package com.egci428.ex07_cardview

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egci428.ex07_cardview.Adapter.MovieAdapter
import com.egci428.ex07_cardview.Model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    private val jsonURL = "https://egci428-d78f6-default-rtdb.firebaseio.com/movies.json"

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)

        recyclerView.layoutManager = linearLayoutManager

        loadJson()

    }

    private fun loadJson() {
        val client = OkHttpClient()
        val loadJsonAsync = object: AsyncTask<String, String, String>(){
            override fun doInBackground(vararg params: String): String {
                val builder = Request.Builder()
                builder.url(params[0])
                val request = builder.build()
                try {
                    val response = client.newCall(request).execute()
                    return response.body.string()

                } catch (e:Exception) {
                    e.printStackTrace()
                }
                return ""
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                var movieObject: List<Movie>
                movieObject = Gson().fromJson<List<Movie>>(result,object : TypeToken<List<Movie>>() {}.type)
                val adapter = MovieAdapter(movieObject)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onPreExecute() {
                Toast.makeText(this@MainActivity,"Please wait", Toast.LENGTH_SHORT).show()
            }

        }
        val url_get_data = StringBuilder()
        url_get_data.append(jsonURL)
        loadJsonAsync.execute(url_get_data.toString())
    }
}