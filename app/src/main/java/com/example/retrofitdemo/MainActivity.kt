package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.adopters.NewsAdopter
import com.example.retrofitdemo.apiService.NewsService
import com.example.retrofitdemo.models.News
import com.example.retrofitdemo.models.UserPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adopter: NewsAdopter
    lateinit var newsList :RecyclerView
    lateinit var textView: TextView
    lateinit var txt2: TextView
    lateinit var txt3: TextView
    lateinit var txt4: TextView

    lateinit var editTextEmail : EditText
    lateinit var buttonSignup : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textVIew)
        editTextEmail = findViewById(R.id.editId)
         txt2 = findViewById(R.id.textVIew2)
         txt3 = findViewById(R.id.textVIew3)
         txt4 = findViewById(R.id.textVIew4)
        getNews()
        sendUserpost()

        buttonSignup.setOnClickListener {
            val email = editTextEmail.text.toString().trim()

        }


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
                    //comment dd
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("TAG___>","Error in fatcing api",t)
            }

        })
    }

    private fun sendUserpost(){

//        var userArray = ArrayList<UserPost>()
//        userArray.add(UserPost(2,2,"title","this is body"))
//        userArray.add(UserPost(3,3,"title3","this is body3"))

        val userPosts = UserPost(2,2,"title","this is body")
       // val retrofitData = NewsService.newsInstance2.delete(1)
        val retrofitData = NewsService.newsInstance2.sendUserData(userPosts)

        retrofitData.enqueue(object : Callback<UserPost>{
            override fun onResponse(call: Call<UserPost>, response: Response<UserPost>) {
               var reposeBody = response.body()
               // textView.text = response.code().toString()
                reposeBody?.apply {
                    textView.text = id.toString()
                    txt2.text = userId.toString()
                    txt3.text = title.toString()
                    txt4.text = body
                }

            }

            override fun onFailure(call: Call<UserPost>, t: Throwable) {
                textView.text = t.message.toString()
            }

        })
    }
}