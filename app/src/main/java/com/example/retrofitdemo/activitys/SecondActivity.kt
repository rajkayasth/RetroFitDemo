package com.example.retrofitdemo.activitys

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.retrofitdemo.R
import com.example.retrofitdemo.apiService.RetrofitClient
import com.example.retrofitdemo.apiService.RetrofitsClient
import com.example.retrofitdemo.models.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var txt2: TextView
    lateinit var txt3: TextView
    lateinit var txt4: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textView = findViewById(R.id.textVIewAlbumID)
        txt2 = findViewById(R.id.textVIewId)
        txt3 = findViewById(R.id.txtViewTitle)
        txt4 = findViewById(R.id.txtViewUrl)

        sendUserPhotos()
    }

    private fun sendUserPhotos() {
        val retrofitData = RetrofitsClient.getInstance().sendPhotos(1001,2002,"Title","https://via.placeholder.com/600/771796","https://via.placeholder.com/600/771796")
        retrofitData.enqueue(object : Callback<UserData>{
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                var reposeBody = response.body()
                txt2.text = reposeBody.toString()
//                reposeBody?.apply {
//                    textView.text = albumID.toString()
//                    txt2.text = id.toString()
//                    txt3.text = title.toString()
//                    txt4.setImageURI(Uri.parse(url))
//                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}