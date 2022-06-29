package com.example.retrofitdemo.apiService

import com.example.retrofitdemo.models.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/v2/everything?q=tesla&from=2022-05-28&sortBy=publishedAt&apiKey=360f79fc21b04883aa6d58f675c76282
// https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY


const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "360f79fc21b04883aa6d58f675c76282"

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadline(@Query("country") country: String, @Query("page") page: Int): Call<News>



}

object NewsService {
    val newsInstance: NewsInterface

    init {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}