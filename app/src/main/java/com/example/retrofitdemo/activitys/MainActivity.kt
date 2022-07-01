package com.example.retrofitdemo.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.R
import com.example.retrofitdemo.adopters.NewsAdopter
import com.example.retrofitdemo.apiService.RetrofitsClient
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

    lateinit var editTextid : EditText
    lateinit var buttonSignup : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textVIew)
        editTextid = findViewById(R.id.editId)
         txt2 = findViewById(R.id.textVIew2)
         txt3 = findViewById(R.id.textVIew3)
         txt4 = findViewById(R.id.textVIew4)
        buttonSignup = findViewById(R.id.btnAdd)
        //getNews()
        sendUserpost()
        deletePost()
        buttonSignup.setOnClickListener {
           val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)

        }



    }

    private fun deletePost() {
        val retrofitData = RetrofitsClient.getInstance().delete(202)
        Log.d("WebAccess", RetrofitsClient.getInstance().toString())
        retrofitData.enqueue(object : Callback<UserPost>{
            override fun onResponse(call: Call<UserPost>, response: Response<UserPost>) {

                var reposeBody = response.body()
                 textView.text = response.code().toString()
//                reposeBody?.apply {
//                    textView.text = id.toString()
//                    txt2.text = userId.toString()
//                    txt3.text = title.toString()
//                    txt4.text = body
//                }

            }

            override fun onFailure(call: Call<UserPost>, t: Throwable) {
                textView.text = t.message.toString()
            }

        })
    }

//    private fun getNews() {
//        val news = RetrofitClient.instance.getHeadline("in",1)
//        news.enqueue(object : Callback<News>{
//            override fun onResponse(call: Call<News>, response: Response<News>) {
//                val news = response.body()
//                if (news != null){
//                    Log.d("TAG___>",news.toString())
//                    adopter = NewsAdopter(this@MainActivity,news.articles)
//                    newsList = findViewById(R.id.newsList)
//                    newsList.adapter = adopter
//                    newsList.layoutManager = LinearLayoutManager(this@MainActivity)
//                    //comment dd
//                }
//            }
//
//            override fun onFailure(call: Call<News>, t: Throwable) {
//                Log.d("TAG___>","Error in fatcing api",t)
//            }
//
//        })
//    }

    private fun sendUserpost(){
        val retrofitData = RetrofitsClient.getInstance().sendUserData(202,230,"TitleNew ","BodyNew")
        Log.d("WebAccess", RetrofitsClient.getInstance().toString())
        retrofitData.enqueue(object : Callback<UserPost>{
            override fun onResponse(call: Call<UserPost>, response: Response<UserPost>) {
               val reposeBody = response.body()
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