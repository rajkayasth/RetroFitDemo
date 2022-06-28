package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adopter: NewsAdopter
    lateinit var newsList :RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadline("in",1)
        news.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null){
                    Log.d("TAG___>",news.toString())
                    adopter = NewsAdopter(this@MainActivity,news.articles)
                    newsList = findViewById(R.id.newsList)
                    newsList.adapter = adopter
                    newsList.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("TAG___>","Error in fatcing api",t)
            }

        })
    }
}